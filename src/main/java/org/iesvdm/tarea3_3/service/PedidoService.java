package org.iesvdm.tarea3_3.service;

import org.iesvdm.tarea3_3.dao.PedidoDAO;
import org.iesvdm.tarea3_3.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PedidoService {
    /*igual que los otros dos solo que ese tiene algunas funciones más*/
    @Autowired
    private PedidoDAO pedidoDAO;
    public void create(Pedido p){
        pedidoDAO.create(p);
    }
    public List<Pedido> listAllByComercial(int id){
        return pedidoDAO.getAllByComercial(id);
    }
    public List<Pedido> listAllByCliente(int id){
        return pedidoDAO.getAllByCliente(id);
    }
    public List<Pedido> listAllByComercialAndCliente(int id_comercial,int id_cliente){
        return pedidoDAO.getAllByComercialAndCliente(id_comercial,id_cliente);
    }

    public Optional<Pedido> findPedido(int id){
        return pedidoDAO.find(id);
    }
    public void deletePedido(int id){
        pedidoDAO.delete(id);
    }
    public void update(Pedido p) {
        pedidoDAO.update(p);
    }
}
