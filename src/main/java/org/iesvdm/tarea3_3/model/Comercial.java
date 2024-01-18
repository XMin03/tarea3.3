package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {
    /*parametros de la base de datos*/
    int id;

    @NotNull(message = "Nombre no puede ser nulo.")
    @NotBlank(message = "Nombre no puede ser vacío.")
    String nombre;

    @NotNull(message = "Apellido1 no puede ser nulo.")
    @NotBlank(message = "Apellido1 no puede ser vacío.")
    String apellido1;

    @NotNull(message = "Apellido2 no puede ser nulo.")
    @NotBlank(message = "Apellido2 no puede ser vacío.")
    String apellido2;

    @NotNull(message = "Comision no puede ser nulo.")
    @Min(value = 0,message = "Minimo {value} ")
    float comision;

    public Comercial() {

    }
}
