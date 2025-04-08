package com.seplag.servidores.repository;

import com.seplag.servidores.entity.Cidade;
import com.seplag.servidores.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    boolean existsByNomeAndUf(String nome, Estado uf);
}
