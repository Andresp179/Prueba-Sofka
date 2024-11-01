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

import com.example.demo.Models.Persona;
import com.example.demo.Service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	    @Autowired
	    private PersonaService personaService; // Asegúrate de tener un servicio que maneje la lógica

	    @PostMapping("/guardar")
	    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
	        Persona nuevaPersona = personaService.save(persona);
	        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Persona> obtenerPersona(@PathVariable Long id) {
	        Persona persona = personaService.findById(id);
	        return persona != null ? 
	            new ResponseEntity<>(persona, HttpStatus.OK) : 
	            new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @GetMapping("/obtener")
	    public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
	        List<Persona> personas = personaService.findAll();
	        return new ResponseEntity<>(personas, HttpStatus.OK);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
	        Persona personaActualizada = personaService.actualizarPersona(id, persona);
	        return personaActualizada != null ? 
	            new ResponseEntity<>(personaActualizada, HttpStatus.OK) : 
	            new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
	        personaService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

}
