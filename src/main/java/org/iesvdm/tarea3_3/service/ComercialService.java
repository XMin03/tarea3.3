package org.iesvdm.tarea3_3.service;

import org.iesvdm.tarea3_3.dao.DAO;
import org.iesvdm.tarea3_3.dao.PedidoDAO;
import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {
    @Autowired
    private DAO<Comercial> comercialDAO;
    @Autowired
    private PedidoDAO pedidoDAO;
    public void create(Comercial c){
        comercialDAO.create(c);
    }
    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }
    public Optional<Comercial> find(int id){
        return comercialDAO.find(id);
    }
    public void delete(int id){
        comercialDAO.delete(id);
    }
    public void update(Comercial c){
        comercialDAO.update(c);
    }
    public void create(Pedido p){
        pedidoDAO.create(p);
    }
    public List<Pedido> listAll(int id){
        return pedidoDAO.getAllByComercial(id);
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
    public String toName(long id){;
        return pedidoDAO.toName(id);
    }
}
