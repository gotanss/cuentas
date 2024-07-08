package com.demo.cuenta.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cuenta.service.CuentaService;
import com.demo.cuenta.util.CuentaRequestDTO;
import com.demo.cuenta.util.CuentaResponseDTO;
import com.demo.cuenta.util.reportes.CuentaReporteDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1/cuenta")
@AllArgsConstructor
public class CuentaController {

   private CuentaService cuentaService;

   @PostMapping
   public CuentaResponseDTO crearCuenta(@RequestBody @Validated CuentaRequestDTO request) {
      return cuentaService.crearCuenta(request);
   }

   @GetMapping("/{cuentaId}")
   public CuentaResponseDTO getCuentaById(@PathVariable Long cuentaId) {
      return cuentaService.getCuenta(cuentaId);

   }

   public void updateCuenta(@RequestBody @Validated CuentaRequestDTO request) {

   }

   public void eliminarCuenta(@RequestBody @Validated CuentaRequestDTO request) {

   }

   @GetMapping
   public List<CuentaResponseDTO> getAllCuenta() {
      return cuentaService.getAllCuentas();

   }

   @GetMapping("/{clienteId}/reportes")
   public List<CuentaReporteDto> getReporteCuentas(@PathVariable Long clienteId,
         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {

      return cuentaService.getReporte(clienteId, desde, hasta);

   }
}
