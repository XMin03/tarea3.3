package org.iesvdm.tarea3_3.controller;

import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.service.ClienteService;
import org.iesvdm.tarea3_3.service.ComercialService;
import org.iesvdm.tarea3_3.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ComercialService comercialService;
    @GetMapping("/comerciales/{id_comercial}/pedidos/crear")
    public String crearPedido(Model model, @PathVariable int id_comercial) {
        Pedido p = new Pedido();
        model.addAttribute("pedido", p);
        model.addAttribute("clientes",clienteService.listAll());
        model.addAttribute("id_comercial", id_comercial);
        return "formPedido";
    }
    @PostMapping("/comerciales/{id_comercial}/pedidos/crear")
    public RedirectView submitCrearPedido(@ModelAttribute Pedido c, @PathVariable int id_comercial) {
        pedidoService.create(c);
        return new RedirectView("/comerciales/"+id_comercial);
    }
    @GetMapping("/comerciales/{id_comercial}/pedidos/editar/{id}")
    public String editarPedido(Model model,@PathVariable int id_comercial, @PathVariable int id){
        Optional<Pedido> p=pedidoService.findPedido(id);
        if (p.isPresent()){
            model.addAttribute("pedido", p.get());
            model.addAttribute("clientes",clienteService.listAll());
            model.addAttribute("comerciales",comercialService.listAll());
            model.addAttribute("id_comercial", id_comercial);
            return "editarPedido";
        } else {
            return "comerciales";
        }
    }
    @PostMapping("/comerciales/{id_comercial}/pedidos/editar")
    public RedirectView submitEditarPedido(Model model,@PathVariable int id_comercial, @ModelAttribute Pedido p){
        pedidoService.update(p);
        return new RedirectView("/comerciales/"+id_comercial);
    }
    @PostMapping("/comerciales/{id_comercial}/pedidos/borrar/{id}")
    public RedirectView borrarPedido(Model model,@PathVariable int id_comercial, @PathVariable int id){
        pedidoService.deletePedido(id);
        return new RedirectView("/comerciales/"+id_comercial);
    }
}
