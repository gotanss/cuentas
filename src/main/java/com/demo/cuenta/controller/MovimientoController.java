package com.demo.cuenta.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cuenta.service.MovimientoService;
import com.demo.cuenta.util.MovRequestDTO;
import com.demo.cuenta.util.MovResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1/movimiento")
@AllArgsConstructor
public class MovimientoController {

   private MovimientoService movimientoService;

   @PostMapping
   public MovResponseDTO crearMov(@RequestBody @Validated MovRequestDTO request) throws AccountNotFoundException {
      return movimientoService.crearMov(request);
   }

   @GetMapping("/{movId}")
   public MovResponseDTO getMovById(@PathVariable Long movId) {
      return movimientoService.getMovimiento(movId);

   }

   public void updateMov(@RequestBody @Validated MovRequestDTO request) {

   }

   public void eliminarMov(@RequestBody @Validated MovRequestDTO request) {

   }

   @GetMapping
   public List<MovResponseDTO> getAllMov() {
      return movimientoService.getAllMov();

   }
}
