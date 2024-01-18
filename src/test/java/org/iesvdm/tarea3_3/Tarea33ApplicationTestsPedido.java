package org.iesvdm.tarea3_3;

import org.iesvdm.tarea3_3.dao.PedidoDAOImpl;
import org.iesvdm.tarea3_3.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class Tarea33ApplicationTestsPedido {

    @Autowired
    private PedidoDAOImpl pedidoDAOImpl;
    @Test
    void test_listAll(){
        List<Pedido> list=pedidoDAOImpl.getAll();
        Assertions.assertFalse(list.isEmpty());
    }
    @Test
    void test_actualizar(){
        Pedido pedido = new Pedido(0
                , 0
                ,new Date(0)
                , 1
                , 1);
        this.pedidoDAOImpl.create(pedido);
        pedido.setTotal(1);
        pedidoDAOImpl.update(pedido);
        Pedido c=pedidoDAOImpl.find(pedido.getId()).get();
        Assertions.assertEquals(c, pedido);
        pedido.setTotal(3);
        c=pedidoDAOImpl.find(pedido.getId()).get();
        Assertions.assertNotEquals(c, pedido);
    }
    @Test
    void test_borrar(){
        Pedido pedido = new Pedido(0
                , 0
                ,new Date(0)
                , 1
                , 1);
        this.pedidoDAOImpl.create(pedido);
        List<Pedido> antes=pedidoDAOImpl.getAll();
        pedidoDAOImpl.delete(pedido.getId());
        List<Pedido> despues=pedidoDAOImpl.getAll();
        Assertions.assertTrue(antes.size()>despues.size());
    }
    @Test
    void test_buscar(){
        Pedido pedido = new Pedido(0
                , 0
                ,new Date(0)
                , 1
                , 1);
        this.pedidoDAOImpl.create(pedido);
        Optional<Pedido> c=pedidoDAOImpl.find(pedido.getId());
        Assertions.assertTrue(c.isPresent()&&c.get().getId()==pedido.getId());
    }
}
