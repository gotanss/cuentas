package com.demo.cuenta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.cuenta.entity.Cuenta;
import com.demo.cuenta.entity.ETipoCuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

   @Query(value = "SELECT *" + "FROM cuenta " + "WHERE cuenta_id = :id " + "LIMIT 1 " + "FOR UPDATE", nativeQuery = true)
   Optional<Cuenta> findByIdForUpdate(Long id);

   List<Cuenta> findByCliente(Long clienteId);

   boolean existsCuentaByClienteAndTipoCuenta(Long idClient, ETipoCuenta tipoCuenta);

}
