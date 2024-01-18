package org.iesvdm.tarea3_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
    /*parametros de la base de datos*/
    int id;
    String nombre;
    String apellido1;
    String apellido2;
    String ciudad;
    int categoria;

    public Cliente() {

    }
}
