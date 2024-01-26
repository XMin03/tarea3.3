package org.iesvdm.tarea3_3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO{
    /*           IGUALES QUE LAS OTRAS DOS CON DOS FUNCIONES MAS                  */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Pedido pedido) {
        String sql= """
                insert into pedido (total,fecha,id_cliente,id_comercial) values(?,?,?,?)
                """;
        KeyHolder k = new GeneratedKeyHolder();
        int row=jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
                int idx = 1;
                ps.setDouble(idx++,pedido.getTotal());
                ps.setDate(idx++,new Date(pedido.getFecha().getTime()));
                ps.setInt(idx++,pedido.getCliente().getId());
                ps.setInt(idx,pedido.getComercial().getId());
                return ps;
            },k);
        pedido.setId(k.getKey().intValue());
        log.info("Insertados {} registros.", row);
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> p= jdbcTemplate.query("select * from pedido",(this::obtain));
        log.info("Devueltos {} registros.", p.size());
        return p;
    }

    /**
     * obtiene todos los pedidos segun la id del comercial
     * @param id id del comercial
     * @return
     */
    @Override
    public List<Pedido> getAllByComercial(int id) {
        List<Pedido> p= jdbcTemplate.query("select * from pedido where id_comercial=?",(this::obtain),id);
        log.info("Devueltos {} registros.", p.size());
        return p;
    }
    public List<Pedido> getAllByCliente(int id){
        List<Pedido> p= jdbcTemplate.query("select * from pedido where id_cliente=?",(this::obtain),id);

        log.info("Devueltos {} registros.", p.size());
        return p;
    }
    public List<Pedido> getAllByComercialAndCliente(int id_comercial,int id_cliente){
        List<Pedido> p= jdbcTemplate.query("select * from pedido where id_comercial=? and id_cliente=?",(this::obtain), id_comercial,id_cliente);
        log.info("Devueltos {} registros.", p.size());
        return p;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido p=jdbcTemplate.queryForObject("select * from pedido where id=?",(this::obtain),id);
        return p==null?Optional.empty():Optional.of(p);
    }

    @Override
    public void update(Pedido pedido) {
        int rows=jdbcTemplate.update("update pedido set total=?, fecha=?, id_cliente=?,id_comercial=? where id=?",
                pedido.getTotal(),
                pedido.getFecha(),
                pedido.getCliente().getId(),
                pedido.getComercial().getId(),
                pedido.getId());
        log.info("Update de Pedido con {} registros actualizados.", rows);
    }

    @Override
    public void delete(long id) {
        int rows=jdbcTemplate.update("delete from pedido where id=?", id);
        log.info("Delete de Pedido con {} registros actualizados.", rows);
    }
    /**
     * devuelve el nombre del cliente segun la id
     * @param id  id del cliente
     * @return
     */
    public String toName(long id){
        return jdbcTemplate.queryForObject("Select nombre from cliente where id=?" ,
                new Object[]{id},
                String.class);
    }
    private Pedido obtain(ResultSet rs, int rows) throws SQLException {
        return new Pedido(
                rs.getInt("pedido.id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                jdbcTemplate.queryForObject("Select * from cliente where id=?" ,
                        (rs1, rowNum1) -> new Cliente(rs1.getInt("id"),
                                rs1.getString("nombre"),
                                rs1.getString("apellido1"),
                                rs1.getString("apellido2"),
                                rs1.getString("ciudad"),
                                rs1.getInt("categoría"),
                                rs1.getString("email"))
                        , rs.getInt("id_cliente")),
                jdbcTemplate.queryForObject("Select * from comercial where id=?",
                        ((rs1, rowNum1) -> new Comercial(
                                rs1.getInt("id"),
                                rs1.getString("nombre"),
                                rs1.getString("apellido1"),
                                rs1.getString("apellido2"),
                                rs1.getBigDecimal("comisión")
                        )),rs.getInt("id_comercial")));
    }
}
