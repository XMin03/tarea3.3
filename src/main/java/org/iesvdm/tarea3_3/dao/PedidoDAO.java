package org.iesvdm.tarea3_3.dao;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Pedido;

import java.util.List;
import java.util.Optional;

/**
 * DAO Con algunas cositas más
 */
public interface PedidoDAO extends DAO<Pedido>{

    public List<Pedido> getAllByComercial(int id);
    public List<Pedido> getAllByCliente(int id);
    public List<Pedido> getAllByComercialAndCliente(int id_comercial,int id_cliente);
    public String toName(long id);
}
