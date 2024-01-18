package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
    /*parametros de la base de datos*/
    int id;
    @NotNull(message = "Nombre no puede ser nulo.")
    @NotBlank(message = "Nombre no puede ser vacío.")
    String nombre;

    @NotNull(message = "Apellido1 no puede ser nulo.")
    @NotBlank(message = "Apellido1 no puede ser vacío.")
    String apellido1;

    String apellido2;

    @NotNull(message = "Ciudad no puede ser nulo.")
    @NotBlank(message = "Ciudad no puede ser vacío.")
    String ciudad;

    @NotNull(message = "Categoria no puede ser nulo.")
    @Min(value = 1,message = "Minimo {value} ")
    int categoria;

    public Cliente() {

    }
}
