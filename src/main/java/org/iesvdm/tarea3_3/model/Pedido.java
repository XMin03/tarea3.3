package org.iesvdm.tarea3_3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.tarea3_3.dao.PedidoDAO;
import org.iesvdm.tarea3_3.dao.PedidoDAOImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    /*parametros de la base de datos*/
    int id;

    @NotNull(message = "Total no puede ser nulo.")
    @Min(value = 0,message = "Minimo {value} ")
    double total;

    @NotNull(message = "Fecha no puede ser nulo.")
    @NotBlank(message = "Fecha no puede ser vacío.")
    Date fecha;

    @NotNull(message = "Id_cliente no puede ser nulo.")
    @Min(value = 1,message = "Minimo {value} ")
    int id_cliente;

    @NotNull(message = "Id_comercial no puede ser nulo.")
    @Min(value = 1,message = "Minimo {value} ")
    int id_comercial;

}
