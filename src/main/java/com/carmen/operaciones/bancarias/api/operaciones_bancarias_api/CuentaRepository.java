package com.carmen.operaciones.bancarias.api.operaciones_bancarias_api;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CuentaRepository  extends JpaRepository<Cuenta, Long>{

          // Buscar por número de cuenta
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    // Buscar cuentas con saldo mayor a un valor específico
    List<Cuenta> findBySaldoGreaterThan(double saldo);

    // Buscar cuentas con saldo menor a un valor específico
    List<Cuenta> findBySaldoLessThan(double saldo);

    // Buscar cuentas por un rango de saldo
    List<Cuenta> findBySaldoBetween(double minSaldo, double maxSaldo);

    // Eliminar cuenta por número de cuenta
    @Transactional
    void deleteByNumeroCuenta(String numeroCuenta);

    // Buscar todas las cuentas ordenadas por saldo descendente
    List<Cuenta> findAllByOrderBySaldoDesc();

}
