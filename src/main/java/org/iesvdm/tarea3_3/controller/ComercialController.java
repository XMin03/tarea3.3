package org.iesvdm.tarea3_3.controller;

import org.iesvdm.tarea3_3.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ComercialController {
    @Autowired
    private ComercialService comercialService;

}
