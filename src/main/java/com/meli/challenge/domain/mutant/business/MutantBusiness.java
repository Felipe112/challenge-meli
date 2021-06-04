package com.meli.challenge.domain.mutant.business;

import com.meli.challenge.domain.mutant.exceptions.BusinessException;
import com.meli.challenge.domain.mutant.model.Being;
import com.meli.challenge.domain.mutant.model.Stat;
import com.meli.challenge.repository.IBeingRepository;
import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * Clase en la que se implementan
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-05-31
 * @Version 1.0
 */
@AllArgsConstructor
public class MutantBusiness implements IMutantBusiness {

    private final IBeingRepository repository;
    private static final String REGEX = "[ACGT]+";

    /**
     * Metodo empleado para identificar si una cadena de adn es perteneciente a un mutanta o humano y guardar los
     * registros usa sola ves en la base de datos.
     *
     * @param dna Array con cadena de secuencia
     * @return boolean
     * @throws BusinessException
     */
    @Override
    public boolean isMutant(String[] dna) throws BusinessException {
        var mutant = false;
        validateSequences(dna);
        mutant = getCountSequenceMutant(dna) > 1;
        var beingExistent = repository.findByDna(Arrays.toString(dna));
        if (null == beingExistent) {
            repository.save(Being.builder().dna(Arrays.toString(dna)).mutant(mutant).build());
        }
        return mutant;
    }

    /**
     * Metodo empleado para obtener el conteo de las secuencias correspondientes a un mutante.
     * Valida que una secuencia se repita durante 4 iteaciones de forma vertical, horizontal y diagonales.
     *
     * @param dna
     * @return int
     */
    private int getCountSequenceMutant(String[] dna) {
        int countSequenceMutant = 0;
        for (var i = 0; i < dna.length; i++) {
            char[] bases = dna[i].toUpperCase().toCharArray();
            int limit = bases.length - 3;
            for (var j = 0; j < bases.length; j++) {
                //Vertical
                if (i < limit && equalSequences(bases[j], dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                    countSequenceMutant++;
                }
                //Horizontal
                if (j < limit && equalSequences(bases[j], dna[j + 1].charAt(j), dna[j + 2].charAt(j), dna[j + 3].charAt(j))) {
                    countSequenceMutant++;
                }
                //Diagonal descendiente
                if (j < limit && i < limit && equalSequences(bases[j], dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                    countSequenceMutant++;
                }

                //Diagonal ascendente
                if (i >= 3 && j < limit && i < limit && equalSequences(bases[j], dna[i - 1].charAt(j - 1), dna[i - 2].charAt(j - 2), dna[i - 3].charAt(j - 3))) {
                    countSequenceMutant++;
                }
            }
        }
        return countSequenceMutant;
    }

    /**
     * Metodo pata consultar el estado de los registros almacenados donde encuentro cantidad de muntantes,
     * cantidad de humanos y promedio.
     *
     * @return Stat
     */
    @Override
    public Stat getStats() {
        var countMutant = repository.getStat(true);
        var countHuman = repository.getStat(false);
        float total = repository.count();
        return Stat.builder().countHumanDNA(countHuman).countMutantDNA(countMutant).ratio(countHuman / total).build();
    }

    /**
     * Validaciones previas para garantizar que una cadena se secuencias cumpla las restricciones minimas necesarias
     *
     * @param dna Array con cadena de secuencias
     * @throws BusinessException
     */
    private void validateSequences(String[] dna) throws BusinessException {
        final var init = dna.length;
        if (init < 4) {
            throw new BusinessException("The data sequences do not meet the required length.");
        }
        for (var i = 0; i < dna.length; i++) {
            if (init != dna[i].length()) {
                throw new BusinessException("Problem Data cannot be analyzed because some of the DNA sequence strands do not have the same length.");
            } else {
                String base = dna[i].toUpperCase();
                if (!base.matches(REGEX)) {
                    throw new BusinessException("The sequence does not meet the established standards for identifying nitrogen bases (A,T,C,G).");
                }
            }
        }
    }

    private boolean equalSequences(char a, char b, char c, char d) {
        return (a == b) && (b == c) && (c == d);
    }

}
