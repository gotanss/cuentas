package com.demo.cuenta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long movimientoId;

   @ManyToOne
   @JoinColumn(name = "cuenta_id", nullable = false)
   private Cuenta cuenta;

   @Enumerated(EnumType.STRING)
   private ETipoMov tipoMov;

   private Boolean estado;

   private String descripcion;

   private Double balanceAnterior;

   private Double montoMov;

   private String currency;
}
