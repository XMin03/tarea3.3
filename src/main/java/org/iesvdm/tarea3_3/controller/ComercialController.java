package org.iesvdm.tarea3_3.controller;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ComercialController {
    @Autowired
    private ComercialService comercialService;
    @Autowired
    private PedidoService pedidoService;

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
            List<Pedido> p= pedidoService.listAll(id);
            Map<Pedido,String> map=new HashMap<>();
            p.stream().forEach(pedido->map.put(pedido, pedidoService.toName(pedido.getId_cliente())));
            model.addAttribute("listaPedido", map);
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

}
