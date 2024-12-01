package com.bramh.pruebaPersonas.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bramh.pruebaPersonas.Models.Cosa;
import com.bramh.pruebaPersonas.Models.PersonaDTO;
import com.bramh.pruebaPersonas.Repository.CosaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bramh.pruebaPersonas.Exception.PersonaNotFoundException;
import com.bramh.pruebaPersonas.Models.Persona;
import com.bramh.pruebaPersonas.Repository.PersonaRepository;
import com.bramh.pruebaPersonas.Utils.ApiResponse;
import com.bramh.pruebaPersonas.Utils.Message;

//import reactor.core.publisher.Flux;

@Service
public class PersonaServiceImp {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private CosaClient cosaClient;

    public List<PersonaDTO> getAllPersonas(){

        List<Persona> personas = personaRepository.findAll();

        return personas.stream().map(persona -> {
            List<Cosa> cosas = cosaClient.obtenerCosasPorPropietario(persona.getIdPersona());
            return PersonaDTO.fromEntity(persona, cosas);
        }).collect(Collectors.toList());
    }


    public ApiResponse addPersona(Persona persona){

        this.personaRepository.save(persona);

        return new ApiResponse(Message.PERSONA_SAVE_SUCCESSFULLY, HttpStatus.CREATED.value(), HttpStatus.CREATED, LocalDateTime.now());
    }

    public ApiResponse updatePersona(String id, Persona persona){

        Persona existingPersona = findPersonaByIdOrThrow(id);

        existingPersona.setNombres(persona.getNombres());
        existingPersona.setApellidos(persona.getApellidos());
        existingPersona.setEdad(persona.getEdad());
        existingPersona.setGenero(persona.getGenero());
        existingPersona.setStatus(persona.getStatus());

        personaRepository.save(existingPersona);

        return new ApiResponse(Message.PERSONA_UPDATE_SUCCESSFULLY, HttpStatus.OK.value(),
                HttpStatus.OK, LocalDateTime.now());
    }

    public PersonaDTO findById(String id){

        Persona persona = findPersonaByIdOrThrow(id);
        List<Cosa> cosas = cosaClient.obtenerCosasPorPropietario(persona.getIdPersona());
        return PersonaDTO.fromEntity(persona, cosas);
    }

    public ApiResponse deletePersona(String id){

        Persona persona = findPersonaByIdOrThrow(id);
        personaRepository.delete(persona);

        return new ApiResponse(Message.PERSONA_DELETE_SUCCESSFULLY, HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT, LocalDateTime.now());
    }

    private Persona findPersonaByIdOrThrow(String id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new PersonaNotFoundException(
                        Message.PERSONA_NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));
    }
}
