package com.example.recargas.infrastructure.output.http;

import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.ports.RecargaSaldoHttp;

import org.springframework.web.client.RestTemplate;

public class RecargaRestTemplateAdapter implements RecargaSaldoHttp {

    private final RestTemplate restTemplate;

    public RecargaRestTemplateAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SaldoDto getSaldo(String operador) {
        return restTemplate.getForObject("https://random-data-api.com/api/v2/banks", SaldoDto.class);
    }
}
