package com.demo.cuenta.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.entity.ETipoMov;
import com.demo.cuenta.entity.Movimiento;
import com.demo.cuenta.exception.CuentaNotFoundException;
import com.demo.cuenta.exception.SaldoInsuficienteException;
import com.demo.cuenta.repository.CuentaRepository;
import com.demo.cuenta.repository.MovimientoRepository;
import com.demo.cuenta.util.CuentaMapper;
import com.demo.cuenta.util.CuentaResponseDTO;
import com.demo.cuenta.util.MovMapper;
import com.demo.cuenta.util.MovRequestDTO;
import com.demo.cuenta.util.MovResponseDTO;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovimientoService {

   private MovimientoRepository movimientoRepository;

   private CuentaRepository cuentaRepository;

   @Transactional
   public MovResponseDTO crearMov(MovRequestDTO request) throws AccountNotFoundException {

      Optional<Cuenta> cuenta = Optional.ofNullable(
            cuentaRepository.findByIdForUpdate(request.getCuenta()).orElseThrow(AccountNotFoundException::new));
      Double balanceActual = cuenta.get().getBalance();
      Cuenta cuentaActual = cuenta.get();

      Movimiento mov = MovMapper.INSTANCE.toEntity(request);
      mov.setEstado(true);
      mov.setDescripcion(mov.getTipoMov().name() + " de " + mov.getMontoMov());
      mov.setBalanceAnterior(balanceActual);
      mov.setCuenta(cuentaActual);
      movimientoRepository.save(mov);

      if (mov.getTipoMov().equals(ETipoMov.DEPOSITO)) {
         cuentaActual.setBalance(balanceActual + mov.getMontoMov());
      }

      if (mov.getTipoMov().equals(ETipoMov.RETIRO)) {
         if (balanceActual < mov.getMontoMov()) {
            throw new SaldoInsuficienteException("La cuenta con id: [%s] no tiene suficiente balance.".formatted(cuentaActual.getCuentaId()));
         }
         cuentaActual.setBalance(balanceActual - mov.getMontoMov());
      }

      cuentaRepository.save(cuentaActual);

      return MovMapper.INSTANCE.toDto(mov);
   }

   public MovResponseDTO getMovimiento(Long id) {
      return MovMapper.INSTANCE.toDto(
            movimientoRepository.findById(id).orElseThrow(() -> new CuentaNotFoundException("Moviminto con id [%s] no encontrado.".formatted(id))));
   }

   public List<MovResponseDTO> getAllMov() {
      return MovMapper.INSTANCE.toDtoList(movimientoRepository.findAll());
   }


}
