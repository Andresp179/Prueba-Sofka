package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Movimientos;
import com.example.demo.Models.Persona;
import com.example.demo.Repository.PersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	private final PersonaRepository personaRepository;

	public PersonaService(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}
	
	public List<Persona> findAll(){return personaRepository.findAll();}
	public Persona findById(Long id) {return personaRepository.findById(id).orElse(null);}
	public Persona save(Persona persona) {return personaRepository.save(persona);}
	public void deleteById(Long id) {personaRepository.deleteById(id);}
	public Persona actualizarPersona(Long id, Persona personaActualizada) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona personaExistente = optionalPersona.get();
            personaExistente.setNombre(personaActualizada.getNombre());
            personaExistente.setGenero(personaActualizada.getGenero());
            personaExistente.setEdad(personaActualizada.getEdad());
            personaExistente.setIdentificacion(personaActualizada.getIdentificacion());
            personaExistente.setDireccion(personaActualizada.getDireccion());
            personaExistente.setTelefono(personaActualizada.getTelefono());
            return personaRepository.save(personaExistente); // Guarda la entidad actualizada
        }
        return null; // Retorna null si no se encontró la persona
    }
}
