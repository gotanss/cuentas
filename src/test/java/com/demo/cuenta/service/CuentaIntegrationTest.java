package com.demo.cuenta.service;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.demo.cuenta.entity.ETipoCuenta;
import com.demo.cuenta.util.CuentaRequestDTO;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CuentaIntegrationTest {

   @Autowired
   private WebTestClient webTestClient;

   private static final Random RANDOM = new Random();

   @Test
   void canRegisterAnAccount() {
      // crear request

      CuentaRequestDTO request = CuentaRequestDTO
            .builder()
            .tipoCuenta(ETipoCuenta.AHORROS)
            .cliente(1L)
            .currency("ARS")
            .build();

      //enviar post
      webTestClient
            .post()
            .uri("/v1/cuenta")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), CuentaRequestDTO.class)
            .exchange()
            .expectStatus()
            .isOk();

      //get all cuentas




   }
}
