package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Comercial {
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

    @NotNull(message = "Comision no puede ser nulo.")
    @Min(value = 0,message = "Minimo {value} ")
    @DecimalMin(value="0.276", inclusive = true,message = "minimo {value}")
    @DecimalMax(value="0.946", inclusive = true,message = "maximo {value}")
    BigDecimal comision;

    public Comercial() {

    }
}
