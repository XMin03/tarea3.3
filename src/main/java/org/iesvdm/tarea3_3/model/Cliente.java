package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
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

    @NotNull(message = "{ciudad} {no.nulo}.")
    @NotBlank(message = "{ciudad} {no.vacio}.")
    @Size(max = 50,message = "{maximo} {max} {caracter.plural}.")
    String ciudad;

    @NotNull(message = "{categoria} {no.nulo}.")
    @Min(value = 100,message = "{minimo} {value}.")
    @Max(value = 1000,message = "{maximo} {value}.")
    int categoria;

    @Email(message = "{invalido.formato}.")
    String email;
    public Cliente() {

    }
}
