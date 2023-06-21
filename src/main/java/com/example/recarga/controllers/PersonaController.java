package com.example.recarga.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.recarga.models.Persona;
import com.example.recarga.service.PersonaService;


@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @RequestMapping(value = "personas", method = RequestMethod.GET, produces = "application/json")
    public List<Persona> list() {
        return personaService.findAll();
    }

}
