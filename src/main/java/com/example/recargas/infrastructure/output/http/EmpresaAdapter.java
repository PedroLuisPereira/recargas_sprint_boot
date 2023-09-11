package com.example.recargas.infrastructure.output.http;

import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.ports.HttpSaldo;
import org.springframework.web.client.RestTemplate;

public class EmpresaAdapter {}

// public class EmpresaAdapter implements HttpSaldo {

//     private final RestTemplate restTemplate;

//     public EmpresaAdapter(RestTemplate restTemplate) {
//         this.restTemplate = restTemplate;
//     }

//     @Override
//     public SaldoDto getSaldo() {
//          return restTemplate.getForObject("https://random-data-api.com/api/v2/banks", SaldoDto.class);
//     }
// }
