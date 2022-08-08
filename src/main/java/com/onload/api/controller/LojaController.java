package com.onload.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onload.api.model.Loja;
import com.onload.api.repository.LojaRepository;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaRepository repo;

    @GetMapping("/")
    public ResponseEntity<List<Loja>> findAll(){
        return ResponseEntity.ok(repo.findAll());        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loja> findById(@PathVariable("id") Long id){
        Optional<Loja> loja = repo.findById(id);
        if(loja.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(loja.get());            
        }        
    }

    @PostMapping("/")
    public ResponseEntity<Loja> save(@Valid @RequestBody() Loja loja){
        var lojaSave = repo.save(loja);
        return new ResponseEntity<Loja>(lojaSave,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loja> update(@PathVariable("id") Long id,
        @Valid @RequestBody() Loja loja){
        Optional<Loja> lojaUpdate = repo.findById(id);
        if(lojaUpdate.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            var res=repo.save(loja);
            return ResponseEntity.ok(res);            
        }  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Loja>> delete(@PathVariable("id") Long id){
        Optional<Loja> lojaDelete = repo.findById(id);
        if(lojaDelete.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            repo.delete(lojaDelete.get());
            return ResponseEntity.ok(repo.findAll());            
        }        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
             String fieldName = ((FieldError) error).getField();
             String errorMessage = error.getDefaultMessage();
             errors.put(fieldName,errorMessage);
        });
        return errors;
    }
    
}
