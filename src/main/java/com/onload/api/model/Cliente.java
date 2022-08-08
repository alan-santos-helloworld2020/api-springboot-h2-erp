package com.onload.api.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    @NotBlank(message = "o nome é obrigátorio")
    private String nome;

    @NotBlank(message = "o telefone é obrigátorio")
    private String telefone;

    @NotBlank(message = "o email é obrigátorio")
    private String email;

    @NotBlank(message = "o cep é obrigátorio")
    private String cep;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
}
