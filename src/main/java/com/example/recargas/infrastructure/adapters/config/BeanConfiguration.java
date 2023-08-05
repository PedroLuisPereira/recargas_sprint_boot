package com.example.recargas.infrastructure.adapters.config;

import com.example.recargas.domain.ports.PersonaPuerto;
import com.example.recargas.domain.ports.RabbitMQPuerto;
import com.example.recargas.domain.ports.RecargaPuerto;
import com.example.recargas.domain.service.PersonaService;
import com.example.recargas.domain.service.RecargaService;
import com.example.recargas.infrastructure.adapters.output.persistence.PersonaPersistenceAdapter;
import com.example.recargas.infrastructure.adapters.output.persistence.RecargaPersistenceAdapter;
import com.example.recargas.infrastructure.adapters.output.persistence.mapper.PersonaMapper;
import com.example.recargas.infrastructure.adapters.output.persistence.mapper.RecargaMapper;
import com.example.recargas.infrastructure.adapters.output.persistence.repository.PersonaRepository;
import com.example.recargas.infrastructure.adapters.output.persistence.repository.RecargaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuracion BEANS
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PersonaMapper personaMapper() {
        return new PersonaMapper();
    }

    @Bean
    public PersonaPersistenceAdapter personaPersistenceAdapter(PersonaRepository personaRepository,
            PersonaMapper productMapper) {
        return new PersonaPersistenceAdapter(personaRepository, productMapper);
    }

    @Bean
    public PersonaService personaService(PersonaPersistenceAdapter personaPersistenceAdapter) {
        return new PersonaService(personaPersistenceAdapter);
    }

    @Bean
    public RecargaMapper recargaMapper() {
        return new RecargaMapper();
    }

    @Bean
    public RecargaPersistenceAdapter recargaPersistenceAdapter(RecargaRepository recargaRepository,
            RecargaMapper recargaMapper) {
        return new RecargaPersistenceAdapter(recargaRepository, recargaMapper);
    }

    @Bean
    public RecargaService recargaService(PersonaPuerto personaRepositorio, RecargaPuerto recargaPuerto,
                                         RestTemplate restTemplate, RabbitMQPuerto rabbitMQPuerto
    ) {
        return new RecargaService(personaRepositorio, recargaPuerto, restTemplate, rabbitMQPuerto);
    }

}
