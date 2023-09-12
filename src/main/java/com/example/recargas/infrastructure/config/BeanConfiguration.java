package com.example.recargas.infrastructure.config;

import com.example.recargas.domain.ports.CompraMensaje;
import com.example.recargas.domain.ports.HttpSaldo;
import com.example.recargas.domain.ports.PersonaRepository;
import com.example.recargas.domain.ports.RecargaRepository;
import com.example.recargas.domain.service.PersonaService;
import com.example.recargas.domain.service.RecargaService;
import com.example.recargas.infrastructure.output.http.EmpresaAdapter;
import com.example.recargas.infrastructure.output.persistence.PersonaPersistenceAdapter;
import com.example.recargas.infrastructure.output.persistence.RecargaPersistenceAdapter;
import com.example.recargas.infrastructure.output.persistence.mapper.PersonaMapper;
import com.example.recargas.infrastructure.output.persistence.mapper.RecargaMapper;

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
    public PersonaPersistenceAdapter personaPersistenceAdapter(com.example.recargas.infrastructure.output.persistence.repository.PersonaRepository personaRepository,
                                                               PersonaMapper productMapper) {
        return new PersonaPersistenceAdapter(personaRepository, productMapper);
    }

    @Bean
    public PersonaService personaService(PersonaPersistenceAdapter personaPersistenceAdapter, RecargaRepository recargaRepositorio) {
        return new PersonaService(personaPersistenceAdapter, recargaRepositorio);
    }

    @Bean
    public RecargaMapper recargaMapper() {
        return new RecargaMapper();
    }

    @Bean
    public RecargaPersistenceAdapter recargaPersistenceAdapter(com.example.recargas.infrastructure.output.persistence.repository.RecargaRepository recargaRepository,
                                                               RecargaMapper recargaMapper, PersonaMapper personaMapper) {
        return new RecargaPersistenceAdapter(recargaRepository, recargaMapper, personaMapper);
    }

    @Bean
    public RecargaService recargaService(PersonaRepository personaRepositorio, RecargaRepository recargaRepository,
                                         HttpSaldo httpSaldo, CompraMensaje compraMensaje ) {
        return new RecargaService(personaRepositorio, recargaRepository, httpSaldo, compraMensaje);
    }

    @Bean
    public EmpresaAdapter empresaAdapter(RestTemplate restTemplate) {
        return new EmpresaAdapter(restTemplate);
    }

}
