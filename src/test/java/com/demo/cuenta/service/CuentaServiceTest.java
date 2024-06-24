package com.demo.cuenta.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.entity.ETipoCuenta;
import com.demo.cuenta.repository.CuentaRepository;
import com.demo.cuenta.util.CuentaRequestDTO;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

   @Mock
   private CuentaRepository cuentaRepository;

   private CuentaService underTest;

   @BeforeEach
   void setUp() {
      underTest = new CuentaService(cuentaRepository);
   }

   @Test
   void crearCuenta() {

      CuentaRequestDTO request = CuentaRequestDTO.builder().tipoCuenta(ETipoCuenta.AHORROS).cliente(1L).currency("ARS").build();

      underTest.crearCuenta(request);

      ArgumentCaptor<Cuenta> captor = ArgumentCaptor.forClass(Cuenta.class);

      verify(cuentaRepository).save(captor.capture());

      Cuenta cuentaCaptor = captor.getValue();

      assertThat(cuentaCaptor.getCuentaId()).isNull();
      assertThat(cuentaCaptor.getTipoCuenta()).isEqualTo(ETipoCuenta.AHORROS);
      assertThat(cuentaCaptor.getCliente()).isEqualTo(1L);
      assertThat(cuentaCaptor.getCurrency()).isEqualTo("ARS");
   }
}