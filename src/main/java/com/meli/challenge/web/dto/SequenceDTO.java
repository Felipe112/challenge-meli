package com.meli.challenge.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Clase con la estructura prevista para recibir las peticiones en uno de los servicios expuestos.
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-06-02
 * @Version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SequenceDTO {
    private List<String> dna;
}
