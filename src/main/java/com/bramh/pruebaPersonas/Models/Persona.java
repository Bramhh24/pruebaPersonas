package com.bramh.pruebaPersonas.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Personas")
public class Persona {

    @Id
    private String idPersona;
    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private Boolean status;

    private List<Cosa> cosas;
}
