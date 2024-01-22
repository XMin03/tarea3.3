package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    /*parametros de la base de datos*/
    int id;
    @NotNull(message = "{total} {no.nulo}.")
    @Min(value = 0,message = "{minimo} {value}.")
    double total;

    @NotNull(message = "{fecha} {no.nulo}.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date fecha;

    @NotNull(message = "{id.cliente} {no.nulo}.")
    @Min(value = 1,message = "{minimo} {value}.")
    int id_cliente;

    @NotNull(message = "{id.comercial} {no.nulo}.")
    @Min(value = 1,message = "{minimo} {value}.")
    int id_comercial;

}
