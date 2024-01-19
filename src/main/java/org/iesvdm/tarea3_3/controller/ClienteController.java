package org.iesvdm.tarea3_3.controller;

import jakarta.validation.Valid;
import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    /**
     * Lista todos los clientes y se va a la pagina del crud de cliente que tambien es el index de cliente
     * @param model
     * @return
     */
    @GetMapping("/clientes")
    public String listar(Model model){
        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes";
    }

    /**
     * los mismo que listar pero muestra solo un cliente
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/clientes/{id}")
    public String detalle(Model model,@PathVariable int id){
        //obtiene el cliente
        Optional<Cliente> c=clienteService.find(id);
        if (c.isPresent()){
            //y hace los mismo que listar pero con solo ese cliente
            model.addAttribute("listaClientes", c.get());
            return "clientes";
        } else {
            //si no encuentra vuelve a la pagina principal que no lo hace
            return listar(model);
        }
    }

    /**
     * va a la pagina para crea un cliente
     * @param model
     * @return
     */
    @GetMapping("/clientes/crear")
    public String crear(Model model) {
        //cliente vacio
        Cliente c = new Cliente();
        //dice la accion porque formCliente es sea para crear que para editar
        model.addAttribute("action", "crear");
        //paso el cliente vacio
        model.addAttribute("cliente", c);
        //va al formulario de cliente
        return "formCliente";
    }

    /**
     * Crea en la base de datos y vuelve al index
     * @param c
     * @return
     */
    @PostMapping("/clientes/crear")
    public String submitCrear(Model model, @Valid @ModelAttribute Cliente c, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            model.addAttribute("action", "crear");
            model.addAttribute("cliente", c);
            return "formCliente";
        }
        clienteService.create(c);
        return "redirect:/clientes";
    }

    /**
     * va a la pagina para editar la id
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        //busca el cliente ya existente para obtener los valores que estaba.
        Optional<Cliente> c=clienteService.find(id);
        if (c.isPresent()){
            //lo mismo que crear pero con la accion editar
            model.addAttribute("action", "editar");
            model.addAttribute("cliente", c.get());
            return "formCliente";
        } else {
            //si no encuentra al index, que no va
            return "redirect:/clientes";
        }
    }

    /**
     * Actualiza en la base de dato y vuelve al index
     * @param model
     * @param c
     * @return
     */
    @PostMapping("/clientes/editar")
    public String submitEditar(Model model, @Valid @ModelAttribute Cliente c, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "editar");
            model.addAttribute("cliente", c);
            return "formCliente";
        }
        clienteService.update(c);
        return "redirect:/clientes";
    }

    /**
     * borra el cliente segun la id
     * @param model
     * @param id
     * @return
     */
    @PostMapping("/clientes/borrar/{id}")
    public RedirectView borrar(Model model, @PathVariable int id){
        clienteService.delete(id);
        return new RedirectView("/clientes");
    }

    /* no usado
    @PostMapping("/clientes/buscar")
    public String find(Model model){
        List<Cliente> listaClientes =  clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes";
    }
    */
}
