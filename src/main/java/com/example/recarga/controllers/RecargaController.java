package com.example.recarga.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.recarga.dtos.RecargaDto;
import com.example.recarga.models.Persona;
import com.example.recarga.models.Recarga;
import com.example.recarga.service.PersonaService;
import com.example.recarga.service.RecargaService;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class RecargaController {

    @Autowired
    RecargaService recargaService;

    @Autowired
    PersonaService personaService;

    @RequestMapping(value = "recargas", method = RequestMethod.GET, produces = "application/json")
    public List<Recarga> list() {
        return recargaService.findAll();
    }

    @RequestMapping(value = "recargas", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> store(@RequestBody RecargaDto recargaDto) {

        // buscar compañia
        Persona persona = personaService.findById(recargaDto.getPersona_id());

        // si no existe
        if (persona == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("{ \"id\" : \"Id de la persona no existe\" }");
        }

        Recarga recarga = new Recarga();
        recarga.setValor(recargaDto.getValor());
        recarga.setCelular(recargaDto.getCelular());
        recarga.setOperador(recargaDto.getOperador());
        recarga.setPersona(persona);

        // crear registro
        recarga = recargaService.save(recarga);

        // retornar
        return new ResponseEntity<Recarga>(recarga, HttpStatus.CREATED);
    }

}
