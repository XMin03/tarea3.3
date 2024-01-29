package org.iesvdm.tarea3_3.model.mapstruct;

import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.model.dto.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    /*inecesario*/
    public ClienteDTO createClienteDTO(int id,String nombre, List<Pedido> pedidos);
}
