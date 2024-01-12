package org.iesvdm.tarea3_3;

import org.iesvdm.tarea3_3.dao.ClienteDAOImpl;
import org.iesvdm.tarea3_3.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class ProyectoVentasApplicationTests {
    @Autowired
    private ClienteDAOImpl clienteDAOImpl;
    @Test
    void test_recargar_id_auto_increment_por_ps() {
        Cliente cliente = new Cliente(0
                , "José M."
                , "Martín"
                , "Tejero"
                , "Málaga"
                , 1);
        this.clienteDAOImpl.create_CON_RECARGA_DE_ID_POR_PS(cliente);
        Assertions.assertTrue(cliente.getId()>0);
        System.out.println("ID AUTO_INCREMENT:" + cliente.getId());
    }
    @Test
    void test_recargar_id_auto_increment_por_simplejdbcinsert() {
        Cliente cliente = new Cliente(0
                , "José M."
                , "Martín"
                , "Tejero"
                , "Málaga"
                , 1);
        this.clienteDAOImpl.create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(cliente);
        Assertions.assertTrue(cliente.getId()>0);
        System.out.println("ID AUTO_INCREMENT:" + cliente.getId());


    }

    @Test
    void test_listAll(){
        List<Cliente> list=clienteDAOImpl.getAll();
        Assertions.assertFalse(list.isEmpty());
    }
    @Test
    void test_actualizar(){
        Cliente cliente = new Cliente(0
                , "José M."
                , "Martín"
                , "Tejero"
                , "Málaga"
                , 1);
        this.clienteDAOImpl.create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(cliente);
        cliente.setNombre("A");
        cliente.setApellido1("a");
        cliente.setApellido2("B");
        cliente.setCiudad("b");
        cliente.setCategoria(2);
        clienteDAOImpl.update(cliente);
        Cliente c=clienteDAOImpl.find(cliente.getId()).get();
        Assertions.assertEquals(c, cliente);
        cliente.setCategoria(3);
        c=clienteDAOImpl.find(cliente.getId()).get();
        Assertions.assertNotEquals(c, cliente);
    }
    @Test
    void test_borrar(){
        Cliente cliente = new Cliente(0
                , "José M."
                , "Martín"
                , "Tejero"
                , "Málaga"
                , 1);
        this.clienteDAOImpl.create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(cliente);
        List<Cliente> antes=clienteDAOImpl.getAll();
        clienteDAOImpl.delete(cliente.getId());
        List<Cliente> despues=clienteDAOImpl.getAll();
        Assertions.assertTrue(antes.size()>despues.size());
    }
    @Test
    void test_buscar(){
        Cliente cliente = new Cliente(0
                , "José M."
                , "Martín"
                , "Tejero"
                , "Málaga"
                , 1);
        this.clienteDAOImpl.create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(cliente);
        Optional<Cliente> c=clienteDAOImpl.find(cliente.getId());
        Assertions.assertTrue(c.isPresent()&&c.get().getId()==cliente.getId());
    }
}
