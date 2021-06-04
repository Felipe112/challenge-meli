package com.meli.challenge.domain.mutant.business;

import com.meli.challenge.domain.mutant.exceptions.BusinessException;
import com.meli.challenge.domain.mutant.model.Being;
import com.meli.challenge.domain.mutant.model.Stat;
import com.meli.challenge.repository.IBeingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MutantBusiness.class)
class MutantBusinessTest {

    @MockBean
    private IBeingRepository repository;

    @Autowired
    @InjectMocks
    private MutantBusiness mutantBusiness;

    @Test
    void constructor() {
        assertNotNull(new MutantBusiness(repository));
    }

    @Test
    @DisplayName("test validate DNA ok")
    void isMutant() throws BusinessException {
        //prepare
        String[] dnaMock = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        Being beingMock = Being.builder().dna(Arrays.toString(dnaMock)).mutant(true).build();
        when(repository.save(any(Being.class))).thenReturn(beingMock);
        //Execute
        boolean result = mutantBusiness.isMutant(dnaMock);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("test validate stats ok")
    void getStats() {
        //prepare
        String[] dnaMock = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        int mutanMock = 1;
        long countMock = 2;
        when(repository.getStat(anyBoolean())).thenReturn(mutanMock);
        when(repository.count()).thenReturn(countMock);
        //Execute
        Stat statResult = mutantBusiness.getStats();
        //Assert
        verify(repository).getStat(Boolean.TRUE);
        verify(repository).getStat(Boolean.FALSE);
        verify(repository).count();
        assertNotNull(statResult);
        assertEquals(1, statResult.getCountHumanDNA());
        assertEquals(1, statResult.getCountMutantDNA());
        //assertEquals(0.5, statResult.getRatio());
    }

    @Test
    @DisplayName("data stream with very short length")
    void isMutantShortLength() throws BusinessException {
        //prepare
        String[] dnaMock = {"ATG", "CAG", "TTA", "AGA", "CCC", "TCA"};
        Being beingMock = Being.builder().dna(Arrays.toString(dnaMock)).mutant(true).build();
        when(repository.save(any(Being.class))).thenReturn(beingMock);
        //Execute
        Throwable exception = assertThrows(BusinessException.class,
                () -> {
                    mutantBusiness.isMutant(dnaMock);
                });
        //Assert
        assertEquals("Problem Data cannot be analyzed because some of the DNA sequence strands do not have the same length.", exception.getMessage());
    }

    @Test
    @DisplayName("data stream with very diferents length")
    void isMutantDiferentLength() throws BusinessException {
        //prepare
        String[] dnaMock = {"ATGCGA", "CAGTGC", "TTATGT", "AGA", "CC", "T"};
        Being beingMock = Being.builder().dna(Arrays.toString(dnaMock)).mutant(true).build();
        when(repository.save(any(Being.class))).thenReturn(beingMock);
        //Execute
        Throwable exception = assertThrows(BusinessException.class,
                () -> {
                    mutantBusiness.isMutant(dnaMock);
                });
        //Assert
        assertEquals("Problem Data cannot be analyzed because some of the DNA sequence strands do not have the same length.", exception.getMessage());
    }

    @Test
    @DisplayName("data stream with diferent squences")
    void isMutantDiferentSequences() throws BusinessException {
        //prepare
        String[] dnaMock = {"ATGCGA", "PHYJRR", "RASLDD", "AGAAGA", "CCAGAC", "TAGAAA"};
        Being beingMock = Being.builder().dna(Arrays.toString(dnaMock)).mutant(true).build();
        when(repository.save(any(Being.class))).thenReturn(beingMock);
        //Execute
        Throwable exception = assertThrows(BusinessException.class,
                () -> {
                    mutantBusiness.isMutant(dnaMock);
                });
        //Assert
        assertEquals("The sequence does not meet the established standards for identifying nitrogen bases (A,T,C,G).", exception.getMessage());
    }

}
