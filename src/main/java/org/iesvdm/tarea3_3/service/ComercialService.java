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
    /*igual que cliente service*/
    @Autowired
    private DAO<Comercial> comercialDAO;

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

}
