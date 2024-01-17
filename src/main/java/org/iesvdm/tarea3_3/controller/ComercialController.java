package org.iesvdm.tarea3_3.controller;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ComercialController {
    @Autowired
    private ComercialService comercialService;
    @GetMapping("/comerciales")
    public String listar(Model model){
        List<Comercial> listaComercialas =  comercialService.listAll();
        model.addAttribute("listaComerciales", listaComercialas);
        return "comerciales";
    }
    @GetMapping("/comerciales/{id}")
    public String detalle(Model model,@PathVariable int id){
        Optional<Comercial> c=comercialService.find(id);
        if (c.isPresent()){
            model.addAttribute("listaComerciales", c.get());
            List<Pedido> p= comercialService.listAll(id);
            model.addAttribute("listaPedido", p);
            return "comerciales";
        } else {
            return listar(model);
        }
    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {
        Comercial c = new Comercial();
        model.addAttribute("action", "crear");
        model.addAttribute("comercial", c);
        return "formComercial";
    }
    @PostMapping("/comerciales/crear")
    public RedirectView submitCrear(@ModelAttribute Comercial c) {
        comercialService.create(c);
        return new RedirectView("/comerciales") ;
    }


    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        Optional<Comercial> c=comercialService.find(id);
        if (c.isPresent()){
            model.addAttribute("action", "editar");
            model.addAttribute("comercial", c.get());
            return "formComercial";
        } else {
            return listar(model);
        }
    }
    @PostMapping("/comerciales/editar")
    public RedirectView submitEditar(Model model, @ModelAttribute Comercial c){
        comercialService.update(c);
        return new RedirectView("/comerciales");
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView borrar(Model model, @PathVariable int id){
        comercialService.delete(id);
        return new RedirectView("/comerciales");
    }

    /*
    @PostMapping("/comerciales/buscar")
    public String find(Model model){
        List<Comercial> listaComercials =  comercialService.listAll();
        model.addAttribute("listaComercials", listaComercials);
        return "comerciales";
    }
    */
    @GetMapping("/comerciales/{id_comercial}/pedidos/crear")
    public String crearPedido(Model model,@PathVariable int id_comercial) {
        Pedido p = new Pedido();
        model.addAttribute("pedido", p);
        model.addAttribute("id_comercial", id_comercial);
        return "formPedido";
    }
    @PostMapping("/comerciales/{id_comercial}/pedidos/crear")
    public RedirectView submitCrearPedido(@ModelAttribute Pedido c,@PathVariable int id_comercial) {
        comercialService.create(c);
        return new RedirectView("/comerciales/"+id_comercial);
    }
    @GetMapping("/comerciales/{id_comercial}/pedidos/editar/{id}")
    public String editarPedido(Model model,@PathVariable int id_comercial, @PathVariable int id){
        Optional<Pedido> p=comercialService.findPedido(id);
        if (p.isPresent()){
            model.addAttribute("pedido", p.get());
            model.addAttribute("id_comercial", id_comercial);
            return "editarPedido";
        } else {
            return detalle(model,id_comercial);
        }
    }
    @PostMapping("/comerciales/{id_comercial}/pedidos/editar")
    public RedirectView submitEditarPedido(Model model,@PathVariable int id_comercial, @ModelAttribute Pedido p){
        comercialService.update(p);
        return new RedirectView("/comerciales/"+id_comercial);
    }
    @PostMapping("/comerciales/{id_comercial}/pedidos/borrar/{id}")
    public RedirectView borrarPedido(Model model,@PathVariable int id_comercial, @PathVariable int id){
        comercialService.deletePedido(id);
        return new RedirectView("/comerciales/"+id_comercial);
    }
}
