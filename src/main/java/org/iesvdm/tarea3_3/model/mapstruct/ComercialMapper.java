package org.iesvdm.tarea3_3.model.mapstruct;

import org.iesvdm.tarea3_3.model.Comercial;
import org.iesvdm.tarea3_3.model.Pedido;
import org.iesvdm.tarea3_3.model.dto.ComercialDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
    /*no muy necesario en ese caso*/
    public ComercialDTO comercialAComercialDTO(Comercial comercial, List<Pedido> pedidos);
    public Comercial comercialDTOAComercial(ComercialDTO comercial);


}
