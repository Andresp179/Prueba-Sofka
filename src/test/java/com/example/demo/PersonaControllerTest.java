package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.Controller.PersonaController;
import com.example.demo.Models.Persona;
import com.example.demo.Service.PersonaService;

public class PersonaControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Mock
    private PersonaService personaService;

    @InjectMocks
    private PersonaController personaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personaController).build();
    }
    
    @Test
    public void testObtenerTodasLasPersonas() throws Exception {
        // Crear una lista de personas simuladas
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1L, "John Doe", "Masculino", 30, "123456789", "Calle Falsa 123", "555-1234"));
        personas.add(new Persona(2L,"Ana Gómez", "Femenino", 28, "987654321", "Calle Verdadera 456", "555-5678"));

        // Simular el comportamiento del servicio
        when(personaService.findAll()).thenReturn(personas);

        // Realizar la petición GET
        mockMvc.perform(get("/personas/obtener"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("John Doe"));
                

        // Verificar que se llamó al servicio
        verify(personaService, times(1)).findAll();
    }
}
