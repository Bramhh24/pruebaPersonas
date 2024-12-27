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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import reactor.core.publisher.Flux;

@Service
public class PersonaServiceImp {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private CosaClient cosaClient;

    public Flux<PersonaDTO> getAllPersonas(){

        Flux<Persona> personas = personaRepository.findAll();

        return personas.map(persona -> {
            List<Cosa> cosas = cosaClient.obtenerCosasPorPropietario(persona.getIdPersona());
            return PersonaDTO.fromEntity(persona, cosas);
        });
//        return personas.stream().map(persona -> {
//            List<Cosa> cosas = cosaClient.obtenerCosasPorPropietario(persona.getIdPersona());
//            return PersonaDTO.fromEntity(persona, cosas);
//        }).collect(Collectors.toList());
    }


    public Mono<ApiResponse> addPersona(Persona persona){

        return this.personaRepository.save(persona)
                .map(p ->
                        new ApiResponse(
                                Message.PERSONA_SAVE_SUCCESSFULLY, HttpStatus.CREATED.value(),
                                HttpStatus.CREATED, LocalDateTime.now())
                );
    }

    public Mono<ApiResponse> updatePersona(String id, Persona persona) {
        return findPersonaByIdOrThrow(id) // Buscar Persona existente (reactiva)
                .flatMap(existingPersona -> { // Mapear el resultado reactivo
                    // Actualizar datos
                    existingPersona.setNombres(persona.getNombres());
                    existingPersona.setApellidos(persona.getApellidos());
                    existingPersona.setEdad(persona.getEdad());
                    existingPersona.setGenero(persona.getGenero());
                    existingPersona.setStatus(persona.getStatus());

                    // Guardar en el repositorio (reactivo)
                    return personaRepository.save(existingPersona);
                })
                .map(updatedPersona -> // Respuesta después de actualizar
                        new ApiResponse(
                                Message.PERSONA_UPDATE_SUCCESSFULLY,
                                HttpStatus.OK.value(),
                                HttpStatus.OK,
                                LocalDateTime.now()
                        )
                );
    }

    public Mono<PersonaDTO> findById(String id){

        Mono<Persona> persona = findPersonaByIdOrThrow(id);

        return persona.map(p -> {
            List<Cosa> cosas = cosaClient.obtenerCosasPorPropietario(p.getIdPersona());
            return PersonaDTO.fromEntity(p, cosas);
        });

    }

    public Mono<ApiResponse> deletePersona(String id){

        Mono<Persona> persona = findPersonaByIdOrThrow(id);
        return persona.map(p -> {
            personaRepository.delete(p);
            return new ApiResponse(Message.PERSONA_DELETE_SUCCESSFULLY, HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT, LocalDateTime.now());
        });
    }

    private Mono<Persona> findPersonaByIdOrThrow(String id) {
        return personaRepository.findById(id)
                .switchIfEmpty(Mono.error(new PersonaNotFoundException(
                        Message.PERSONA_NOT_FOUND, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, LocalDateTime.now()
                )));
    }
}
