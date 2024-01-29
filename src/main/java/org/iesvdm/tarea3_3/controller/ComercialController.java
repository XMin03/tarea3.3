package org.iesvdm.tarea3_3.controller;

import jakarta.validation.Valid;
import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.model.dto.ComercialDTO;
import org.iesvdm.tarea3_3.model.mapstruct.ComercialMapper;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Controller
public class ComercialController {
    @Autowired
    private ComercialService comercialService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ComercialMapper comercialMapper;
    /**
     * Lista todos los comerciales y se va a la pagina del crud de comercial que tambien es el index de comercial
     * @param model
     * @return
     */
    @GetMapping("/comerciales")
    public String listar(Model model){
        List<Comercial> listaComercialas =  comercialService.listAll();
        model.addAttribute("listaComerciales", listaComercialas);
        return "comerciales";
    }

    /**
     * Muestra solo los datos de un solo comercial y algunos detalles más
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/comerciales/{id}")
    public String detalle(Model model,@PathVariable int id){
        //obtiene el comercial que se quiere ver los detalles
        Optional<Comercial> comercial=comercialService.find(id);
        if (comercial.isPresent()){
            Comercial c=comercial.get();
            //obtiene todos los pedidos
            List<Pedido> p= pedidoService.listAllByComercial(id);
            model.addAttribute("listaPedido", p);
            //<Cliente,Total>
            List<Map.Entry<Cliente,Double>> clientes=p.stream()
                    .collect(groupingBy(Pedido::getCliente, summingDouble(Pedido::getTotal)))//obtener <Cliente,Total>
                    .entrySet()
                    .stream()
                    .sorted(((o1, o2) -> (int)(o2.getValue()-o1.getValue())))//ordenar segun el total
                    .toList();
            model.addAttribute("listaClientes", clientes);
            //ComercialDTO
            ComercialDTO cdto=comercialMapper.comercialAComercialDTO(c,p);
            model.addAttribute("listaComerciales", cdto);
            return "comerciales";
        } else {
            return "redirect:/comerciales";
        }
    }

    /**
     * va a la pagina para crea un cliente
     * @param model
     * @return
     */
    @GetMapping("/comerciales/crear")
    public String crear(Model model) {
        Comercial c = new Comercial();
        model.addAttribute("action", "crear");
        model.addAttribute("comercial", c);
        return "formComercial";
    }

    /**
     * Comprueba los errores y crea en la base de datos y vuelve al index
     * @param model
     * @param c
     * @param bindingResult
     * @return
     */
    @PostMapping("/comerciales/crear")
    public String submitCrear(Model model,@Valid @ModelAttribute Comercial c,BindingResult bindingResult) {
        //lo mismo que el de cliente pero para comercial
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "crear");
            model.addAttribute("comercial", c);
            return "formComercial";
        }
        comercialService.create(c);
        return "redirect:/comerciales";
    }

    /**
     * va a la pagina para editar la id
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        Optional<Comercial> c=comercialService.find(id);
        if (c.isPresent()){
            model.addAttribute("action", "editar");
            model.addAttribute("comercial", c.get());
            return "formComercial";
        } else {
            return "redirect:/comerciales";
        }
    }

    /**
     * Comprueba los errores y actualiza en la base de datos y vuelve al index
     * @param model
     * @param c
     * @param bindingResult
     * @return
     */
    @PostMapping("/comerciales/editar")
    public String submitEditar(Model model, @Valid @ModelAttribute Comercial c, BindingResult bindingResult){
        //lo mismo que el de cliente pero para comercial
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "editar");
            model.addAttribute("comercial", c);
            return "formComercial";
        }
        comercialService.update(c);
        return "redirect:/comerciales";
    }

    /**
     * borra el cliente segun la id
     * @param model
     * @param id
     * @return
     */
    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView borrar(Model model, @PathVariable int id){
        comercialService.delete(id);
        return new RedirectView("/comerciales");
    }

    /* not used
    @PostMapping("/comerciales/buscar")
    public String find(Model model){
        List<Comercial> listaComercials =  comercialService.listAll();
        model.addAttribute("listaComercials", listaComercials);
        return "comerciales";
    }
    */

}
