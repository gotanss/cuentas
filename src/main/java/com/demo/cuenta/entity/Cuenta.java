package com.demo.cuenta.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long cuentaId;

   @Column(unique = true, nullable = false)
   private String nroCuenta;

   @Enumerated(EnumType.STRING)
   private ETipoCuenta tipoCuenta;

   private Double saldoInicial;

   private Double balance;

   private Boolean estado;

   private String currency;

   private Long cliente;

   @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<Movimiento> movimientos;

   @PrePersist
   public void prePersist() {
      if (nroCuenta == null) {
         nroCuenta = UUID.randomUUID().toString();
      }
   }
}
