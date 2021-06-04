package com.meli.challenge.web;

import com.meli.challenge.domain.mutant.business.IMutantBusiness;
import com.meli.challenge.domain.mutant.exceptions.BusinessException;
import com.meli.challenge.domain.mutant.model.Stat;
import com.meli.challenge.web.dto.Response;
import com.meli.challenge.web.dto.SequenceDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MutantController.class)
class MutantControllerTest {

    @MockBean
    private IMutantBusiness business;

    @Autowired
    @InjectMocks
    private MutantController controller;


    private final String[] dnaMock = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

    @Test
    @DisplayName("test service post is mutant")
    void mutant() throws BusinessException {
        //prepare
        SequenceDTO sequenceDto = SequenceDTO.builder().dna(Arrays.asList(dnaMock)).build();
        when(business.isMutant(dnaMock)).thenReturn(Boolean.TRUE);
        //Execute
        ResponseEntity<Response<Boolean>> responseController = controller.mutant(sequenceDto);
        //Assert
        assertNotNull(responseController);
        assertEquals("200 OK", responseController.getStatusCode().toString());
        assertTrue(responseController.getBody().isState());
    }

    @Test
    @DisplayName("test service post not is mutant")
    void human() throws BusinessException {
        //prepare
        SequenceDTO sequenceDto = SequenceDTO.builder().dna(Arrays.asList(dnaMock)).build();
        when(business.isMutant(dnaMock)).thenReturn(Boolean.FALSE);
        //Execute
        ResponseEntity<Response<Boolean>> responseController = controller.mutant(sequenceDto);
        //Assert
        verify(business).isMutant(dnaMock);
        assertNotNull(responseController);
        assertEquals("403 FORBIDDEN", responseController.getStatusCode().toString());
        assertFalse(responseController.getBody().isState());
    }

    @Test
    @DisplayName("test service stats")
    void stats() throws BusinessException {
        //prepare
        Stat statMock = Stat.builder().build();
        when(business.getStats()).thenReturn(statMock);
        //Execute
        ResponseEntity<Response<Stat>> responseController = controller.stats();
        //Assert
        verify(business).getStats();
        assertNotNull(responseController);
        assertTrue(responseController.getBody().isState());
    }

}
