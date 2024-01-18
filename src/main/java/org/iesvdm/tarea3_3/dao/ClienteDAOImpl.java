package org.iesvdm.tarea3_3.dao;

import org.iesvdm.tarea3_3.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class ClienteDAOImpl implements DAO<Cliente>{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /*              COPIAS DESDE CLASSROOM QUE NO SE HAN USADO              */
    public void create_SIN_RECARGA_DE_ID(Cliente cliente) {
        jdbcTemplate.update("""
                               INSERT INTO cliente
                               (nombre, apellido1, apellido2, ciudad, categoría)
                               VALUE
                               (?, ?, ?, ?, ?)
                               """
                , cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria());


        //NO SE ACTUALIZA EL ID AUTO_INCREMENT DE MYSQL EN EL BEAN DE CLIENTE
    }
    public void create_CON_RECARGA_DE_ID_POR_PS(Cliente cliente) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("""
                               INSERT INTO cliente
                               (nombre, apellido1, apellido2, ciudad, categoría)
                               VALUE
                               (?, ?, ?, ?, ?)
                               """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++, cliente.getCategoria());
            return ps;
        }, keyHolder);
        //SE ACTUALIZA EL ID AUTO_INCREMENT DE MYSQL EN EL BEAN DE CLIENTE MEDIANTE EL KEYHOLDER
        cliente.setId(keyHolder.getKey().intValue());
    }
    public void create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(Cliente cliente) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("cliente")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", cliente.getNombre())
                .addValue("apellido1", cliente.getApellido1())
                .addValue("apellido2", cliente.getApellido2())
                .addValue("ciudad", cliente.getCiudad())
                .addValue("categoría", cliente.getCategoria());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);
        cliente.setId(number.intValue());
    }

    /**
     *  crea el cliente con recarga de la id
     * @param cliente
     */
    @Override
    public void create(Cliente cliente) {
        String insert= """
                INSERT INTO cliente (nombre,apellido1,apellido2,ciudad,categoría)VALUES(?,?,?,?,?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows=jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx, cliente.getCategoria());
            return ps;
        },keyHolder);
        cliente.setId(keyHolder.getKey().intValue());
        log.info("Insertados {} registros.", rows);
    }

    /**
     * obtiene todos los clientes
     * @return
     */
    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes=jdbcTemplate.query("Select * from cliente",
                (rs,rowNum)->new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría")
                        )
        );
        log.info("Devueltos {} registros.", clientes.size());
        return clientes;
    }

    /**
     * busca un cliente segun la id
     * @param id
     * @return
     */
    @Override
    public Optional<Cliente> find(int id) {
        Cliente c=jdbcTemplate.queryForObject("Select * from cliente where id=?" ,
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría"))
                , id);
        if (c != null) {
            return Optional.of(c);}
        else {
            log.info("Cliente no encontrado.");
            return Optional.empty(); }
    }

    /**
     * actualiza los parametros del cliente segun su id
     * @param cliente
     */
    @Override
    public void update(Cliente cliente) {
        int rows=jdbcTemplate.update("update cliente set nombre=?,apellido1=?,apellido2=?,ciudad=?,categoría=? where id=?",
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getCiudad(),
                cliente.getCategoria(),
                cliente.getId());
        log.info("Update de Cliente con {} registros actualizados.", rows);
    }

    /**
     * elimina segun la id
     * @param id
     */
    @Override
    public void delete(long id) {
        int rows=jdbcTemplate.update("delete from cliente where id=?",id);
        log.info("Delete de Cliente con {} registros eliminados.", rows);
    }
}
