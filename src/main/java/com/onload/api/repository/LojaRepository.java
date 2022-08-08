package com.onload.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onload.api.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja,Long> {
    
}
