package org.iesvdm.tarea3_3.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import static java.util.stream.Collectors.summarizingDouble;
public class ComercialDTO extends Comercial {
    public double tot;
    public double media;
    public double max;
    public double min;

    public ComercialDTO(int id, @NotNull(message = "{nombre} {no.nulo}.") @NotBlank(message = "{nombre} {no.vacio}.") @Size(max = 30, message = "{maximo} {max} {caracter.plural}.") String nombre, @NotNull(message = "{apellido1} {no.nulo}.") @NotBlank(message = "{apellido1} {no.vacio}.") @Size(max = 30, message = "{maximo} {max} {caracter.plural}.") String apellido1, String apellido2, @NotNull(message = "{comision} {no.nulo}.") @DecimalMin(value = "0.276", inclusive = true, message = "{minimo} {value}.") @DecimalMax(value = "0.946", inclusive = true, message = "{maximo} {value}.") BigDecimal comision, List<Pedido> pedidos) {
        super(id, nombre, apellido1, apellido2, comision);
        DoubleSummaryStatistics stats=pedidos.stream().collect(summarizingDouble(Pedido::getTotal));
        tot=stats.getSum();
        media=stats.getAverage();
        max=stats.getMax();
        min=stats.getMin();
    }
}
