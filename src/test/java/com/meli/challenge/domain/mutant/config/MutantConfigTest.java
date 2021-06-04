package com.meli.challenge.domain.mutant.config;

import com.meli.challenge.domain.mutant.business.MutantBusiness;
import com.meli.challenge.repository.IBeingRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MutantConfig.class)
class MutantConfigTest {

    @MockBean
    private IBeingRepository repository;

    @Autowired
    @InjectMocks
    private MutantBusiness mutantBusiness;

    @Test
    void mutantBusinessOk() {
        assertNotNull(new MutantBusiness(repository));
    }
}
