package com.meli.challenge.domain.mutant.config;

import com.meli.challenge.domain.mutant.business.IMutantBusiness;
import com.meli.challenge.domain.mutant.business.MutantBusiness;
import com.meli.challenge.repository.IBeingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración con un bean para poder inyectar una instancia de IMutantBusiness
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-05-31
 * @Version 1.0
 */
@Configuration
public class MutantConfig {

    @Bean
    public IMutantBusiness getMutant(IBeingRepository repository) {
        return new MutantBusiness(repository);
    }
}
