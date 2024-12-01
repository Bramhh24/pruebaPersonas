package com.bramh.pruebaPersonas.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaDTO {

    private String idPersona;
    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private Boolean status;
    private List<Cosa> cosas;

    public static PersonaDTO fromEntity(Persona persona, List<Cosa> cosas) {
        return new PersonaDTO(persona.getIdPersona(), persona.getNombres(), persona.getApellidos(),
                persona.getEdad(), persona.getGenero(), persona.getStatus(), cosas);
    }
}
