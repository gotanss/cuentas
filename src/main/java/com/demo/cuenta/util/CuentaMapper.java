package com.demo.cuenta.util;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.entity.Movimiento;
import com.demo.cuenta.util.reportes.ClienteReporteDto;
import com.demo.cuenta.util.reportes.CuentaReporteDto;
import com.demo.cuenta.util.reportes.MovimientoReporteDto;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.EXPLICIT)
public interface CuentaMapper {

   CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

   Cuenta toEntity(CuentaRequestDTO cuentaRequestDTO);

   CuentaResponseDTO toDto(Cuenta cuenta);

   @Mappings({@Mapping (source = "",target = "")})
   List<CuentaResponseDTO> toDtoList(List<Cuenta> cuentaList);

   CuentaReporteDto toReporteDto(Cuenta cuenta);

   List<CuentaReporteDto> toListReporteDto(List<Cuenta> cuenta);

   MovimientoReporteDto movToMovDto(Movimiento movimiento);

   default List<MovimientoReporteDto> mapMovEntityToMovDto(List<Movimiento> movList) {
      return movList.stream()
                  .map(this::movToMovDto)
                  .collect(Collectors.toList());
   }

}
