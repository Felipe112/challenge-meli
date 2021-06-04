package com.meli.challenge.domain.mutant.model;

import lombok.Builder;
import lombok.Data;

/**
 * Clase modelo
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-06-02
 * @Version 1.0
 */
@Data
@Builder
public class Stat {

    /**
     * Cantidad de mutantes
     */
    private int countMutantDNA;
    /**
     * Cantidad de humanos
     */
    private int countHumanDNA;
    /**
     * Promedio
     */
    private float ratio;

}
