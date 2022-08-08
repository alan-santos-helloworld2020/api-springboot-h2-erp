package com.onload.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "data required")
    private LocalDate data = LocalDate.now();

    @NotBlank(message = "nome required")
    private String nome;
    
    @NotBlank(message = "telefone required")
    private String telefone;

    @NotBlank(message = "email required")
    private String email;

    @NotBlank(message = "endereco required")
    private String endereco;
    
    @NotBlank(message = "bairro required")
    private String bairro;

    @NotBlank(message = "cep required")
    private String cep;

    @JsonManagedReference
    @OneToMany(targetEntity = Cliente.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENTE_ID")
    public List<Cliente> clientes;

}
