package org.iesvdm.tarea3_3.controller;

import jakarta.validation.Valid;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.service.ClienteService;
import org.iesvdm.tarea3_3.service.ComercialService;
import org.iesvdm.tarea3_3.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    /**
     * se va a la pagina para crear pedidos
     * @param model
     * @param id_comercial
     * @return
     */
    @GetMapping("/comerciales/{id_comercial}/pedidos/crear")
    public String crearPedido(Model model, @PathVariable int id_comercial) {
        Pedido p = new Pedido();
        //ya se sabe de cual comercial es y no se cambia
        Comercial c=new Comercial();
        c.setId(id_comercial);
        p.setComercial(c);
        model.addAttribute("pedido", p);
        //obtiene todos los clientes para el select
        model.addAttribute("clientes",clienteService.listAll());
        model.addAttribute("comerciales",comercialService.listAll());

        return "formPedido";
    }

    /**
     * Crea en la base de datos y vuelve a la pagina del comercial que pertenece
     * @param p
     * @param id_comercial
     * @return
     */
    @PostMapping("/comerciales/{id_comercial}/pedidos/crear")
    public String submitCrearPedido(Model model,@Valid @ModelAttribute Pedido p,  BindingResult bindingResult, @PathVariable int id_comercial) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pedido", p);
            model.addAttribute("clientes",clienteService.listAll());
            return "formPedido";
        }
        pedidoService.create(p);
        return "redirect:/comerciales/"+id_comercial;
    }

    /**
     * va a la pagina para editar la id
     * @param model
     * @param id_comercial
     * @param id
     * @return
     */
    @GetMapping("/comerciales/{id_comercial}/pedidos/editar/{id}")
    public String editarPedido(Model model,@PathVariable int id_comercial, @PathVariable int id){
        Optional<Pedido> p=pedidoService.findPedido(id);
        if (p.isPresent()){
            model.addAttribute("pedido", p.get());
            //obtiene todos los clientes y comerciales para los selects
            model.addAttribute("clientes",clienteService.listAll());
            model.addAttribute("comerciales",comercialService.listAll());
            return "editarPedido";
        } else {
            return "redirect:/comerciales/"+id_comercial;
        }
    }

    /**
     * Actualiza en la base de dato y vuelve al comercial padre
     * @param model
     * @param id_comercial
     * @param p
     * @return
     */
    @PostMapping("/comerciales/{id_comercial}/pedidos/editar")
    public String submitEditarPedido(Model model, @Valid @ModelAttribute Pedido p, BindingResult bindingResult, @PathVariable int id_comercial){
        if (bindingResult.hasErrors()) {
            model.addAttribute("pedido", p);
            model.addAttribute("clientes",clienteService.listAll());
            model.addAttribute("comerciales",comercialService.listAll());
            return "editarPedido";
        }
        pedidoService.update(p);
        return "redirect:/comerciales/"+id_comercial;
    }

    /**
     * borra el pedido segun la id.
     * @param model
     * @param id_comercial
     * @param id
     * @return
     */
    @PostMapping("/comerciales/{id_comercial}/pedidos/borrar/{id}")
    public RedirectView borrarPedido(Model model,@PathVariable int id_comercial, @PathVariable int id){
        pedidoService.deletePedido(id);
        return new RedirectView("/comerciales/"+id_comercial);
    }
}
