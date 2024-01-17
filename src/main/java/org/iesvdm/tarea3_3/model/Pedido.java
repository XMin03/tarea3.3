package org.iesvdm.tarea3_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    int id;
    double total;
    Date fecha;
    int id_cliente;
    int id_comercial;
}
