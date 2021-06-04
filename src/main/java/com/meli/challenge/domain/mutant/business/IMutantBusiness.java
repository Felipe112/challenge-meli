package com.meli.challenge.domain.mutant.business;

import com.meli.challenge.domain.mutant.exceptions.BusinessException;
import com.meli.challenge.domain.mutant.model.Stat;

public interface IMutantBusiness {

    public boolean isMutant(String[] dna) throws BusinessException;

    public Stat getStats();
}
