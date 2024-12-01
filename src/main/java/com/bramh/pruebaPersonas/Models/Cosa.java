package com.bramh.pruebaPersonas.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cosa {

    private Long idCosa;
    private String tipo;
    private String nombre;
    private String descripcion;
    private String propietario; // idPersona de MongoDB
    private Boolean status;
}
