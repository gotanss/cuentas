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
public class MovRequestDTO {

   private ETipoMov tipoMov;

   private Double montoMov;

   private String currency;

   private Long cuenta;
}
