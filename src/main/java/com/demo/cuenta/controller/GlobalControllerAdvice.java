package com.demo.cuenta.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.cuenta.exception.CuentaNotFoundException;
import com.demo.cuenta.exception.DuplicateResourceException;
import com.demo.cuenta.exception.ErrorCatalog;
import com.demo.cuenta.exception.ErrorResponse;
import com.demo.cuenta.exception.SaldoInsuficienteException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalControllerAdvice {

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(CuentaNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleCuentaNotFound(CuentaNotFoundException e, HttpServletRequest request, HttpServletResponse response) {
      ErrorResponse errorResponse = ErrorResponse
            .builder()
            .path(request.getRequestURI())
            .status(HttpStatus.NOT_FOUND.value())
            .message(ErrorCatalog.ACCOUNT_NOT_FOUND.getMessage())
            .timestamp(LocalDateTime.now())
            .build();

      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      BindingResult result = ex.getBindingResult();
      return ErrorResponse
            .builder()
            .code(ErrorCatalog.INVALID_ACCOUNT.getCode())
            .message(ErrorCatalog.INVALID_ACCOUNT.getMessage())
            .details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
            .timestamp(LocalDateTime.now())
            .build();
   }

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   public ErrorResponse handleGenericError(Exception ex) {
      return ErrorResponse
            .builder()
            .code(ErrorCatalog.GENERIC_ERROR.getCode())
            .message(ErrorCatalog.GENERIC_ERROR.getMessage())
            .details(Collections.singletonList(ex.getMessage()))
            .timestamp(LocalDateTime.now())
            .build();
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(SaldoInsuficienteException.class)
   public ResponseEntity<ErrorResponse> handleSaldoInsuficienteException(SaldoInsuficienteException e, HttpServletRequest request, HttpServletResponse response) {
      ErrorResponse errorResponse = ErrorResponse
            .builder()
            .path(request.getRequestURI())
            .status(HttpStatus.BAD_REQUEST.value())
            .message(ErrorCatalog.ACCOUNT_INSUFFICIENT_BALANCE.getMessage())
            .timestamp(LocalDateTime.now())
            .build();

      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }

   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler(DuplicateResourceException.class)
   public ErrorResponse handleGenericError(HttpServletRequest request) {
      return ErrorResponse
            .builder()
            .path(request.getRequestURI())
            .status(HttpStatus.CONFLICT.value())
            .message(ErrorCatalog.ACCOUNT_CREATION_ERROR.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
   }
}
