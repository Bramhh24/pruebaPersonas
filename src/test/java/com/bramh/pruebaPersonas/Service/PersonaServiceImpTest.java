package com.bramh.pruebaPersonas.Service;

import com.bramh.pruebaPersonas.Models.Cosa;
import com.bramh.pruebaPersonas.Models.Persona;
import com.bramh.pruebaPersonas.Repository.CosaClient;
import com.bramh.pruebaPersonas.Repository.PersonaRepository;
import com.bramh.pruebaPersonas.Utils.ApiResponse;
import com.bramh.pruebaPersonas.Utils.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PersonaServiceImpTest {

    @Mock
    private PersonaRepository personaRepository;
    @InjectMocks
    private PersonaServiceImp personaServiceImp;
    @Mock
    private CosaClient cosaClientMock;
    private Persona persona;
    private Optional<Persona> optPersona;
    private Flux<Persona> personas;
    private List<Cosa> cosas = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        persona = new Persona();
        persona.setIdPersona("123abc");
        persona.setNombres("Test");
        persona.setApellidos("Testeo");
        persona.setEdad(20);
        persona.setGenero("Test");
        persona.setStatus(true);

        List<Persona> listaPersonas = new ArrayList<Persona>();
        listaPersonas.add(persona);
        personas = Flux.fromIterable(listaPersonas);

        optPersona = Optional.of(persona);

        Cosa cosa = new Cosa();
        cosa.setIdCosa(1L);
        cosa.setNombre("Cosa");
        cosa.setDescripcion("Cosa");
        cosa.setTipo("Objeto Cosa");
        cosa.setStatus(true);
        cosa.setPropietario("123abc");
        cosas.add(cosa);

        Mockito.when((personaRepository.findAll())).thenReturn(personas);
        Mockito.when(personaRepository.save(any(Persona.class))).thenReturn(Mono.just(persona));
        Mockito.when((personaRepository.findById(""))).thenReturn(Mono.just(persona));
        Mockito.when(cosaClientMock.obtenerCosasPorPropietario("")).thenReturn(cosas);
    }

    @Test
    void allPersonas(){
        assertNotNull(personaServiceImp.getAllPersonas());
    }

    @Test
    void addPersona() {
        assertNotNull(personaServiceImp.addPersona(new Persona()));
    }

    @Test
    void updatePersona() {
        assertNotNull(personaServiceImp.updatePersona("", new Persona()));
    }

    @Test
    void findById() {
        assertNotNull(personaServiceImp.findById(""));
    }

    @Test
    void deletePersona() {
        assertNotNull(personaServiceImp.deletePersona(""));
    }
}