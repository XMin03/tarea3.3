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



    @GetMapping("/clientes/crear")
    public String crear(Model model) {
        Cliente c = new Cliente();
        model.addAttribute("cliente", c);
        return "formCrearCliente";
    }
    @PostMapping("/clientes/crear")
    public RedirectView submitCrear(@ModelAttribute Cliente c) {
        clienteService.create(c);
        return new RedirectView("/clientes") ;
    }



    @PostMapping("/clientes/borrar/{id}")
    public RedirectView borrar(Model model, @PathVariable int id){
        clienteService.delete(id);
        return new RedirectView("/clientes");
    }




    @PostMapping("/clientes/buscar")
    public String find(Model model){
        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes";
    }




    @PostMapping("/clientes/actualizar/")
    public String actualizar(Model model){
        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes";
    }


}
