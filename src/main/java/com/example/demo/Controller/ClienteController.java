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

import com.example.demo.Models.Cliente;
import com.example.demo.Models.Persona;
import com.example.demo.Service.ClienteService;

@RestController
@RequestMapping
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
		Cliente clienteExistente = clienteService.findById(id);

		if (clienteExistente != null) {
			// Actualizar datos específicos del cliente
			clienteExistente.setContraseña(clienteDetails.getContraseña());
			clienteExistente.setEstado(clienteDetails.isEstado());

			// Actualizar los datos de la persona asociada
			Persona personaExistente = clienteExistente.getPersona();
			Persona personaNueva = clienteDetails.getPersona();

			if (personaExistente != null && personaNueva != null) {
				personaExistente.setNombre(personaNueva.getNombre());
				personaExistente.setGenero(personaNueva.getGenero());
				personaExistente.setEdad(personaNueva.getEdad());
				personaExistente.setIdentificacion(personaNueva.getIdentificacion());
				personaExistente.setDireccion(personaNueva.getDireccion());
				personaExistente.setTelefono(personaNueva.getTelefono());
			}

			Cliente clienteActualizado = clienteService.save(clienteExistente);
			return ResponseEntity.ok(clienteActualizado);
		}

		return ResponseEntity.notFound().build();
	}

}

