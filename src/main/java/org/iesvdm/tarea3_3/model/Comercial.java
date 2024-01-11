package org.iesvdm.tarea3_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {
    int id;
    String nombre;
    String apellido1;
    String apellido2;
    float comision;
}
