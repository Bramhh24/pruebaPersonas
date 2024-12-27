package com.bramh.pruebaPersonas.Controllers;

import java.net.URI;

import com.bramh.pruebaPersonas.Models.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bramh.pruebaPersonas.Models.Persona;
import com.bramh.pruebaPersonas.Service.PersonaServiceImp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaServiceImp personaServiceImp;

    @GetMapping("/all")
    public Mono<ResponseEntity<Flux<PersonaDTO>>> getAllPersonas(){

        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.personaServiceImp.getAllPersonas())
        );
    }

    @GetMapping("/{personaId}")
    public Mono<ResponseEntity<PersonaDTO>> getPersonaId(@PathVariable String personaId){

        return this.personaServiceImp.findById(personaId).map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PostMapping("/addPersona")
    public Mono<ResponseEntity<?>> addPersona(@RequestBody Persona persona){

//        this.personaServiceImp.addPersona(persona);
//
//        URI location = ServletUriComponentsBuilder
//                        .fromCurrentRequest()
//                        .path("/{personaId}")
//                        .buildAndExpand(persona.getIdPersona())
//                        .toUri();
//
//        return ResponseEntity.created(location).body(persona);

        return this.personaServiceImp.addPersona(persona).map(apiResponse -> ResponseEntity
                .created(URI.create("/api/v1/persona/".concat(persona.getIdPersona())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiResponse)
        );
    }

    @PutMapping("/update/{personaId}")
    public Mono<ResponseEntity<?>> updatePersona(@PathVariable String personaId, @RequestBody Persona persona){

        //return ResponseEntity.ok(this.personaServiceImp.updatePersona(personaId, persona));

        return this.personaServiceImp.updatePersona(personaId, persona).map(apiResponse -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiResponse)
        );
    }

    @DeleteMapping("/delete/{personaId}")
    public Mono<ResponseEntity<?>> deletePersona(@PathVariable String personaId){

        //return ResponseEntity.ok(this.personaServiceImp.deletePersona(personaId));
        return this.personaServiceImp.deletePersona(personaId).map(apiResponse -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiResponse)
        );
    }
}
