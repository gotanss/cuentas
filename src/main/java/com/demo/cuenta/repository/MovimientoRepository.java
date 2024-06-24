package com.demo.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cuenta.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

}
