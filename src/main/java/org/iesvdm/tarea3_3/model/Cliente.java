package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
    /*parametros de la base de datos*/
    int id;
    @NotNull(message = "Nombre no puede ser nulo.")
    @NotBlank(message = "Nombre no puede ser vacío.")
    @Size(max = 30,message = "Maximo {max} caracteres")
    String nombre;

    @NotNull(message = "Apellido1 no puede ser nulo.")
    @NotBlank(message = "Apellido1 no puede ser vacío.")
    @Size(max = 30,message = "Maximo {max} caracteres")
    String apellido1;

    String apellido2;

    @NotNull(message = "Ciudad no puede ser nulo.")
    @NotBlank(message = "Ciudad no puede ser vacío.")
    @Size(max = 50,message = "Maximo {max} caracteres")
    String ciudad;

    @NotNull(message = "Categoria no puede ser nulo.")
    @Min(value = 100,message = "Minimo {value} ")
    @Max(value = 1000,message = "Maximo {value} ")
    int categoria;

    public Cliente() {

    }
}
