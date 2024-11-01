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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Cuenta;
import com.example.demo.Service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@GetMapping
	public List<Cuenta> getAllCuentas() {
		return cuentaService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
		Cuenta cuenta = cuentaService.findById(id);
		return cuenta != null ? ResponseEntity.ok(cuenta) : ResponseEntity.notFound().build();
	}

	@PostMapping("/guardar")
	public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
		return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
		Cuenta cuenta = cuentaService.findById(id);
		if (cuenta != null) {
			cuenta.isEstado();
			// (Actualiza otros atributos según sea necesario)
			return ResponseEntity.ok(cuentaService.save(cuenta));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
		cuentaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
