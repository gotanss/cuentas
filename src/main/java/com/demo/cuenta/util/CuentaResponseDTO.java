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
public class CuentaResponseDTO {

   private ETipoCuenta tipoCuenta;

   private Double saldoInicial;

   private Double balance;

   private Boolean estado;

   private String currency;

   private Long cliente;
}
