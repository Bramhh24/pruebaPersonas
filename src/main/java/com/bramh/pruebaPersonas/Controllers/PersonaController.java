package com.bramh.pruebaPersonas.Controllers;

import java.net.URI;
import java.util.List;

import com.bramh.pruebaPersonas.Models.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bramh.pruebaPersonas.Models.Persona;
import com.bramh.pruebaPersonas.Service.PersonaServiceImp;


@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaServiceImp personaServiceImp;

    @GetMapping("/all")
    public ResponseEntity<List<PersonaDTO>> getAllPersonas(){

        return ResponseEntity.ok(this.personaServiceImp.getAllPersonas());
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<PersonaDTO> getPersonaId(@PathVariable String personaId){

        return ResponseEntity.ok(this.personaServiceImp.findById(personaId));
    }

    @PostMapping("/addPersona")
    public ResponseEntity<?> addPersona(@RequestBody Persona persona){

        this.personaServiceImp.addPersona(persona);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{personaId}")
                        .buildAndExpand(persona.getIdPersona())
                        .toUri();

        return ResponseEntity.created(location).body(persona);
    }

    @PutMapping("/update/{personaId}")
    public ResponseEntity<?> updatePersona(@PathVariable String personaId, @RequestBody Persona persona){

        return ResponseEntity.ok(this.personaServiceImp.updatePersona(personaId, persona));
    }

    @DeleteMapping("/delete/{personaId}")
    public ResponseEntity<?> deletePersona(@PathVariable String personaId){

        return ResponseEntity.ok(this.personaServiceImp.deletePersona(personaId));
    }
}
