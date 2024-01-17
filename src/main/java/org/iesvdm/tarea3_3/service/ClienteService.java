package org.iesvdm.tarea3_3.service;

import org.iesvdm.tarea3_3.dao.DAO;
import org.iesvdm.tarea3_3.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private DAO<Cliente> clienteDAO;
    public void create(Cliente c){
        clienteDAO.create(c);
    }
    public List<Cliente> listAll(){
        return clienteDAO.getAll();
    }
    public Optional<Cliente> find(int id){
        return clienteDAO.find(id);
    }
    public void delete(int id){
        clienteDAO.delete(id);
    }
    public void update(Cliente c){
        clienteDAO.update(c);
}

}
