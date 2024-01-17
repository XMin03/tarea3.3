package org.iesvdm.tarea3_3.dao;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);

    public List<Pedido> getAllByComercial(int id);
    public Optional<Pedido> find(int id);

    public void update(Pedido pedido);

    public void delete(long id);
}
