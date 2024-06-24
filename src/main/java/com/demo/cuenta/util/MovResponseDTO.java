package com.demo.cuenta.util;

import com.demo.cuenta.entity.ETipoMov;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovResponseDTO {

   private ETipoMov tipoMov;

   private Boolean estado;

   private String descripcion;

   private Double balanceAnterior;

   private Double montoMov;

   private String currency;
}
