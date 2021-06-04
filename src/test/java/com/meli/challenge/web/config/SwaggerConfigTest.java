package com.meli.challenge.web.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SwaggerConfig.class)
public class SwaggerConfigTest {

    @InjectMocks
    @Autowired
    private SwaggerConfig swaggerConfig;

    @Test
    public void nullNotTest() {
        Docket actual = swaggerConfig.document();
        assertNotNull(actual);
    }

}
