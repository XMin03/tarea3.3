package org.iesvdm.tarea3_3.model.dto;

import org.iesvdm.tarea3_3.model.Cliente;
import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* NO TIENE NADA A QUE VER CON CLIENTE SI NO LO USA.*/
public class ClienteDTO {
    public int id_comercial;
    public String nombre;
    public long lustro;
    public long año;
    public long semestre;
    public long trimestre;

    public ClienteDTO(int id,String nombre,List<Pedido> pedidos) {
        id_comercial=id;
        this.nombre= nombre;
        List<LocalDate> fecha=pedidos.stream()
                .map(Pedido::getFecha)
                .map(Date::getTime)
                .map(Instant::ofEpochMilli)
                .map(instant -> instant.atZone(ZoneId.systemDefault()))
                .map(ZonedDateTime::toLocalDate)
                .toList();
        lustro=fecha.stream().filter(localDate -> localDate.isAfter(LocalDate.now().minusYears(5))).count();
        año=fecha.stream().filter(localDate -> localDate.isAfter(LocalDate.now().minusYears(1))).count();
        semestre=fecha.stream().filter(localDate -> localDate.isAfter(LocalDate.now().minusMonths(6))).count();
        trimestre=fecha.stream().filter(localDate -> localDate.isAfter(LocalDate.now().minusMonths(3))).count();
    }
}
