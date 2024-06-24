package com.demo.cuenta.util;

import com.demo.cuenta.entity.ETipoCuenta;
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
public class CuentaRequestDTO {

   private ETipoCuenta tipoCuenta;

   private String currency;

   private Long cliente;
}
