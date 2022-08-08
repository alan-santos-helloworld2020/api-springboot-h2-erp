package com.onload.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.onload.api.model.Cliente;
import com.onload.api.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(repo.findAll());        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Long id){
        Optional<Cliente> cliente = repo.findById(id);
        if(cliente.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(cliente.get());            
        }        
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> save(@Valid @RequestBody() Cliente cliente){
        var svCliente = repo.save(cliente);
        return new ResponseEntity<Cliente>(svCliente,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id,
        @Valid @RequestBody() Cliente cliente){
        Optional<Cliente> clienteUpdate = repo.findById(id);
        if(clienteUpdate.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            var res=repo.save(cliente);
            return ResponseEntity.ok(res);            
        }  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Cliente>> delete(@PathVariable("id") Long id){
        Optional<Cliente> cliente = repo.findById(id);
        if(cliente.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            repo.delete(cliente.get());
            return ResponseEntity.ok(repo.findAll());            
        }        
    }

    
}
