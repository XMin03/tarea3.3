package org.iesvdm.tarea3_3.service;

import org.iesvdm.tarea3_3.dao.ComercialDAO;
import org.iesvdm.tarea3_3.model.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {
    @Autowired
    private ComercialDAO comercialDAO;
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
