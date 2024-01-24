package org.iesvdm.tarea3_3.model.mapstruct;

import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.model.dto.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    public ClienteDTO createClienteDTO(int id, List<Pedido> pedidos);
}
