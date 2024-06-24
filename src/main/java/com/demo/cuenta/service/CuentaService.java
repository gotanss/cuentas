package com.demo.cuenta.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.exception.CuentaNotFoundException;
import com.demo.cuenta.repository.CuentaRepository;
import com.demo.cuenta.util.CuentaMapper;
import com.demo.cuenta.util.CuentaRequestDTO;
import com.demo.cuenta.util.CuentaResponseDTO;
import com.demo.cuenta.util.reportes.ClienteReporteDto;
import com.demo.cuenta.util.reportes.CuentaReporteDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CuentaService {

   private final CuentaRepository cuentaRepository;

   public CuentaResponseDTO crearCuenta(CuentaRequestDTO request) {
      Cuenta cuenta = CuentaMapper.INSTANCE.toEntity(request);
      cuenta.setEstado(true);
      cuenta.setBalance(0.0);
      cuenta.setSaldoInicial(0.0);
      cuentaRepository.save(cuenta);
      return CuentaMapper.INSTANCE.toDto(cuenta);
   }

   public CuentaResponseDTO getCuenta(Long id) {
      return CuentaMapper.INSTANCE.toDto(
            cuentaRepository.findById(id).orElseThrow(() -> new CuentaNotFoundException("Cuenta con id [%s] no encontrada.".formatted(id))));
   }

   public List<CuentaResponseDTO> getAllCuentas() {
      return CuentaMapper.INSTANCE.toDtoList(cuentaRepository.findAll());
   }

   public List<CuentaReporteDto> getReporte(Long id, LocalDateTime desde, LocalDateTime hasta) {
//      CuentaReporteDto cuentaReporteDto = cuentaRepository.findByClienteWithMov(id);

      List<Cuenta> cuentas = cuentaRepository.findByCliente(id);


      return CuentaMapper.INSTANCE.toListReporteDto(cuentas);

   }
}
