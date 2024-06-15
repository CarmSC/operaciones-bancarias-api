package com.carmen.operaciones.bancarias.api.operaciones_bancarias_api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cuenta {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(name="numero_cuenta")
      private String numeroCuenta;

      private double saldo;

      public Cuenta() {
      }

      public Cuenta(Long id, String numeroCuenta, double saldo) {
            this.id = id;
            this.numeroCuenta = numeroCuenta;
            this.saldo = saldo;
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getNumeroCuenta() {
            return numeroCuenta;
      }

      public void setNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
      }

      public double getSaldo() {
            return saldo;
      }

      public void setSaldo(double saldo) {
            this.saldo = saldo;
      }

}
