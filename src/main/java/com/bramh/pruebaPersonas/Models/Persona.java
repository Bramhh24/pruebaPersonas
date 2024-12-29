package com.bramh.pruebaPersonas.Models;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
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
    @NotEmpty
    private String nombres;
    @NotEmpty
    private String apellidos;
    @NotNull
    @Min(value = 1)
    private int edad;
    @NotEmpty
    private String genero;
    @NotNull
    private Boolean status;

    private List<Cosa> cosas;
}
