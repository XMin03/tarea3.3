package org.iesvdm.tarea3_3.controller;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.service.ClienteService;
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
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/clientes")
    public String listar(Model model){
        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes";
    }
    @GetMapping("/clientes/{id}")
    public String detalle(Model model,@PathVariable int id){
        Optional<Cliente> c=clienteService.find(id);
        if (c.isPresent()){
            model.addAttribute("listaClientes", c.get());
            return "clientes";
        } else {
            return listar(model);
        }
    }

    @GetMapping("/clientes/crear")
    public String crear(Model model) {
        Cliente c = new Cliente();
        model.addAttribute("action", "crear");
        model.addAttribute("cliente", c);
        return "formCliente";
    }
    @PostMapping("/clientes/crear")
    public RedirectView submitCrear(@ModelAttribute Cliente c) {
        clienteService.create(c);
        return new RedirectView("/clientes") ;
    }


    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        Optional<Cliente> c=clienteService.find(id);
        if (c.isPresent()){
            model.addAttribute("action", "editar");
            model.addAttribute("cliente", c.get());
            return "formCliente";
        } else {
            return listar(model);
        }
    }
    @PostMapping("/clientes/editar")
    public RedirectView submitEditar(Model model, @ModelAttribute Cliente c){
        clienteService.update(c);
        return new RedirectView("/clientes");
    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView borrar(Model model, @PathVariable int id){
        clienteService.delete(id);
        return new RedirectView("/clientes");
    }

    /*
    @PostMapping("/clientes/buscar")
    public String find(Model model){
        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes";
    }
    */
}
