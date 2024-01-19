package org.iesvdm.tarea3_3;

import org.iesvdm.tarea3_3.dao.ComercialDAOImpl;
import org.iesvdm.tarea3_3.model.Comercial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class Tarea33ApplicationTestsComercial {
    @Autowired
    private ComercialDAOImpl comercialDAOimpl;
    @Test
    void test_listAll(){
        List<Comercial> list=comercialDAOimpl.getAll();
        Assertions.assertFalse(list.isEmpty());
    }
    @Test
    void test_actualizar(){
        Comercial comercial = new Comercial(0
                , "José M."
                , "Martín"
                , "Tejero"
                , BigDecimal.ONE);
        this.comercialDAOimpl.create(comercial);
        comercial.setNombre("A");
        comercial.setApellido1("a");
        comercial.setApellido2("B");
        comercial.setComision(BigDecimal.TWO);
        comercialDAOimpl.update(comercial);
        Comercial c=comercialDAOimpl.find(comercial.getId()).get();
        Assertions.assertEquals(c, comercial);
        comercial.setComision(BigDecimal.TEN);
        c=comercialDAOimpl.find(comercial.getId()).get();
        Assertions.assertNotEquals(c, comercial);
    }
    @Test
    void test_borrar(){
        Comercial comercial = new Comercial(0
                , "José M."
                , "Martín"
                , "Tejero"
                ,  BigDecimal.ONE);
        this.comercialDAOimpl.create(comercial);
        List<Comercial> antes=comercialDAOimpl.getAll();
        comercialDAOimpl.delete(comercial.getId());
        List<Comercial> despues=comercialDAOimpl.getAll();
        Assertions.assertTrue(antes.size()>despues.size());
    }
    @Test
    void test_buscar(){
        Comercial comercial = new Comercial(0
                , "José M."
                , "Martín"
                , "Tejero"
                ,  BigDecimal.ONE);
        this.comercialDAOimpl.create(comercial);
        Optional<Comercial> c=comercialDAOimpl.find(comercial.getId());
        Assertions.assertTrue(c.isPresent()&&c.get().getId()==comercial.getId());
    }
}
