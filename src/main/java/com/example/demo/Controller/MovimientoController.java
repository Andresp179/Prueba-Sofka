package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Movimientos;
import com.example.demo.Service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;

	@GetMapping
	public List<Movimientos> getAllMovimientos() {
		return movimientoService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movimientos> getMovimientoById(@PathVariable Long id) {
		Movimientos movimiento = movimientoService.findById(id);
		return movimiento != null ? ResponseEntity.ok(movimiento) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Movimientos> createMovimiento(@RequestBody Movimientos movimientos) {
		return new ResponseEntity<>(movimientoService.save(movimientos), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movimientos> updateMovimiento(@PathVariable Long id,
			@RequestBody Movimientos movimientoDetails) {
		Movimientos movimiento = movimientoService.findById(id);
		if (movimiento != null) {
			movimiento.setValor(movimientoDetails.getValor());
			movimiento.setSaldo(movimientoDetails.getSaldo());
			// (Actualiza otros atributos según sea necesario)
			return ResponseEntity.ok(movimientoService.save(movimiento));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
		movimientoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/registrar")
	public ResponseEntity<Movimientos> registrarMovimiento(@RequestParam Long cuentaId,
			@RequestParam String tipoMovimiento, @RequestParam double valor) {

		Movimientos movimiento = movimientoService.registrarMovimiento(cuentaId, tipoMovimiento, valor);
		return ResponseEntity.ok(movimiento);
	}

}
