package com.demo.cuenta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SaldoInsuficienteException extends RuntimeException {

   public SaldoInsuficienteException(String message) {
      super(message);

   }
}
