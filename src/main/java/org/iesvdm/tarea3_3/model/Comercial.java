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

    @NotNull(message = "{nombre} {no.nulo}.")
    @NotBlank(message = "{nombre} {no.vacio}.")
    @Size(max = 30,message = "{maximo} {max} {caracter.plural}.")
    String nombre;

    @NotNull(message = "{apellido1} {no.nulo}.")
    @NotBlank(message = "{apellido1} {no.vacio}.")
    @Size(max = 30,message = "{maximo} {max} {caracter.plural}.")
    String apellido1;

    String apellido2;

    @NotNull(message = "{comision} {no.nulo}.")
    @DecimalMin(value="0.276", inclusive = true,message = "{minimo} {value}.")
    @DecimalMax(value="0.946", inclusive = true,message = "{maximo} {value}.")
    BigDecimal comision;

    public Comercial() {

    }
}
