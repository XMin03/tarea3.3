package org.iesvdm.tarea3_3.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    /**
     * El unico que lleva todos los errores a la misma pagina
     * @param model
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String exeption(Model model, Exception e){
        model.addAttribute("number","404");
        model.addAttribute("error",e);
        return "error";
    }
}
