package com.demo.cuenta.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.entity.Movimiento;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.EXPLICIT)
public interface MovMapper {

   MovMapper INSTANCE = Mappers.getMapper(MovMapper.class);

   @Mappings({
         @Mapping(source = "cuenta",target = "cuenta")
   })
   Movimiento toEntity(MovRequestDTO movRequestDTO);

   MovResponseDTO toDto(Movimiento movimiento);

   Cuenta map(Long value);

   List<MovResponseDTO> toDtoList(List<Movimiento> movList);
}
