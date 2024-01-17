package org.iesvdm.tarea3_3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO{
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
                ps.setDate(idx++,pedido.getFecha());
                ps.setInt(idx++,pedido.getId_cliente());
                ps.setInt(idx,pedido.getId_comercial());
                return ps;
            },k);
        log.info("Insertados {} registros.", row);
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> p= jdbcTemplate.query("select * from pedido",((rs, rowNum) -> new Pedido(
                rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                rs.getInt("id_cliente"),
                rs.getInt("id_comercial"))));
        log.info("Devueltos {} registros.", p.size());
        return p;
    }

    @Override
    public List<Pedido> getAllByComercial(int id) {
        List<Pedido> p= jdbcTemplate.query("select * from pedido where id_comercial=?",((rs, rowNum) -> new Pedido(
                rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                rs.getInt("id_cliente"),
                rs.getInt("id_comercial"))),id);
        log.info("Devueltos {} registros.", p.size());
        return p;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido p=jdbcTemplate.queryForObject("select * from pedido where id=?",((rs, rowNum) -> new Pedido(
                rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                rs.getInt("id_cliente"),
                rs.getInt("id_comercial"))),id);
        return p==null?Optional.empty():Optional.of(p);
    }

    @Override
    public void update(Pedido pedido) {
        int rows=jdbcTemplate.update("update pedido set total=?, fecha=?, id_cliente=?,id_comercial=? where id=?",
                pedido.getTotal(),
                pedido.getFecha(),
                pedido.getId_cliente(),
                pedido.getId_comercial(),
                pedido.getId());
        log.info("Update de Pedido con {} registros actualizados.", rows);
    }

    @Override
    public void delete(long id) {
        int rows=jdbcTemplate.update("delete from pedido where id=?", id);
        log.info("Delete de Pedido con {} registros actualizados.", rows);
    }
    public String toName(long id){
        return jdbcTemplate.queryForObject("Select nombre from cliente where id=?" ,
                new Object[]{id},
                String.class);
    }
}
