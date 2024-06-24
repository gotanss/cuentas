package com.demo.cuenta.util;

import org.springframework.stereotype.Component;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.repository.CuentaRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CuentaMapperAux {

   private CuentaRepository cuentaRepository;

   public Cuenta toCuenta(Long cuentaId) {
      return cuentaRepository.findById(cuentaId).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
   }
}
