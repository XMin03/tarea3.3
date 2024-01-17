package org.iesvdm.tarea3_3.dao;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO extends DAO<Pedido>{

    public List<Pedido> getAllByComercial(int id);
}
