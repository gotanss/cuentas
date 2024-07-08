package com.demo.cuenta.exception;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
   ACCOUNT_NOT_FOUND("ERR_Cuenta_001", "Cuenta no encontrada."),
   ACCOUNT_CREATION_ERROR("ERR_Cuenta_002", "Error al crear cuenta."),
   ACCOUNT_INSUFFICIENT_BALANCE("ERR_Cuenta_003", "Balance insuficiente."),
   INVALID_ACCOUNT("ERR_Cuenta_004","Parametros de cuenta invalidos."),
   GENERIC_ERROR("ERR_Cuenta_005", "Error insesperado.");

   private final String code;

   private final String message;

   ErrorCatalog(String code, String message) {
      this.code = code;
      this.message = message;
   }
}
