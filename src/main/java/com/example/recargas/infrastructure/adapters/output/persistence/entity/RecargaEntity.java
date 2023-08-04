package com.example.recargas.infrastructure.adapters.output.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recargas")
public class RecargaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    private String celular;

    private String operador;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PersonaEntity personaEntity;
    
}
