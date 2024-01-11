package org.iesvdm.tarea3_3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.tarea3_3.model.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class ComercialDAOImpl implements ComercialDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Comercial cliente) {
        KeyHolder k=new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps=con.prepareStatement("insert into comercial (nombre,apellido1,apellido2,comisión) values(?,?,?,?)",new String[]{"id"});
            int i=1;
            ps.setString(i++,cliente.getNombre());
            ps.setString(i++,cliente.getApellido1());
            ps.setString(i++,cliente.getApellido2());
            ps.setFloat(i,cliente.getComision());
            return ps;
        },k);
        cliente.setId(k.getKey().intValue());
    }

    @Override
    public List<Comercial> getAll() {
        List<Comercial> c=jdbcTemplate.query("Select * from comercial",((rs, rowNum) -> new Comercial(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido1"),
                rs.getString("apellido2"),
                rs.getFloat("comisión")
        )));
        log.info("Devueltos {} registros.", c.size());

        return c;

    }

    @Override
    public Optional<Comercial> find(int id) {
        Comercial c=jdbcTemplate.queryForObject("select * from comercial where id=?",((rs, rowNum) -> new Comercial(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido1"),
                rs.getString("apellido2"),
                rs.getFloat("comisión")
                )));
        return (c==null)?Optional.empty():Optional.of(c);
    }

    @Override
    public void update(Comercial cliente) {
        int rows=jdbcTemplate.update("update comercial set nombre=?,apellido1=?,apellido2=?,comisión=? where id=?",
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getComision(),
                cliente.getId());
        log.info("Update de Comercial con {} registros actualizados.", rows);
    }

    @Override
    public void delete(long id) {
        int row=jdbcTemplate.update("delete from comercial where id=?",id);
        log.info("delete de Comercial con {} registros actualizados.", row);
    }
}
