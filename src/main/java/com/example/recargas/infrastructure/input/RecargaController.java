package com.example.recargas.infrastructure.input;

import com.example.recargas.application.recarga.consulta.RecargaListar;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.recargas.application.recarga.comando.RecargaCrear;
import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;

import java.util.List;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class RecargaController {

    private final RecargaCrear recargaCrear;
    private final RecargaListar recargaListar;

    public RecargaController(RecargaCrear recargaCrear, RecargaListar recargaListar) {
        this.recargaCrear = recargaCrear;
        this.recargaListar = recargaListar;
    }

    @GetMapping("/recargas")
    public List<RecargaRespuestaDto> listar() {
        return recargaListar.ejecutar();
    }

    @PostMapping("/recargas")
    @ResponseStatus(HttpStatus.CREATED)
    public RecargaRespuestaDto createPersona(@RequestBody RecargaCrearDto recargaCrearDto) {
        return recargaCrear.ejecutar(recargaCrearDto);
    }

}
