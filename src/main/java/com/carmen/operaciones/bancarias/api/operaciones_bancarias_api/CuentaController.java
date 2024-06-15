package com.carmen.operaciones.bancarias.api.operaciones_bancarias_api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

      @Autowired
    private CuentaRepository cuentaRepository;

    // Endpoint para obtener todas las cuentas
    @GetMapping
    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    // Endpoint para crear una cuenta nueva
    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    // Endpoint para obtener una cuenta por su ID
    @GetMapping("/{id}")
    public Cuenta obtenerCuentaPorId(@PathVariable Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + id));
    }

    // Endpoint para buscar una cuenta por número de cuenta
    @GetMapping("/buscar/{numeroCuenta}")
    public Cuenta obtenerCuentaPorNumero(@PathVariable String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con número: " + numeroCuenta));
    }

    // Endpoint para actualizar una cuenta existente
    @PutMapping("/{id}")
    public Cuenta actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
        return cuentaRepository.findById(id)
                .map(cuenta -> {
                    cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
                    cuenta.setSaldo(cuentaActualizada.getSaldo());
                    return cuentaRepository.save(cuenta);
                })
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + id));
    }

    // Endpoint para eliminar una cuenta por su ID
    @DeleteMapping("/{id}")
    public void eliminarCuenta(@PathVariable Long id) {
        cuentaRepository.deleteById(id);
    }

    // Endpoint para eliminar una cuenta por número de cuenta
    @DeleteMapping("/eliminar/{numeroCuenta}")
    public void eliminarCuentaPorNumero(@PathVariable String numeroCuenta) {
        cuentaRepository.deleteByNumeroCuenta(numeroCuenta);
    }

    // Endpoint para obtener cuentas con saldo mayor a un valor específico
    @GetMapping("/saldoMayorQue/{saldo}")
    public List<Cuenta> obtenerCuentasConSaldoMayorQue(@PathVariable double saldo) {
        return cuentaRepository.findBySaldoGreaterThan(saldo);
    }

    // Endpoint para obtener cuentas con saldo menor a un valor específico
    @GetMapping("/saldoMenorQue/{saldo}")
    public List<Cuenta> obtenerCuentasConSaldoMenorQue(@PathVariable double saldo) {
        return cuentaRepository.findBySaldoLessThan(saldo);
    }

    // Endpoint para obtener cuentas por un rango de saldo
    @GetMapping("/saldoEntre/{minSaldo}/{maxSaldo}")
    public List<Cuenta> obtenerCuentasPorRangoDeSaldo(@PathVariable double minSaldo, @PathVariable double maxSaldo) {
        return cuentaRepository.findBySaldoBetween(minSaldo, maxSaldo);
    }

    // Endpoint para obtener todas las cuentas ordenadas por saldo descendente
    @GetMapping("/ordenadasPorSaldoDesc")
    public List<Cuenta> obtenerCuentasOrdenadasPorSaldoDesc() {
        return cuentaRepository.findAllByOrderBySaldoDesc();
    }
}
