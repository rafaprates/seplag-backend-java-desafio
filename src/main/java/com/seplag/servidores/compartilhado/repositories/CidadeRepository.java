package com.seplag.servidores.compartilhado.repositories;

import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    boolean existsByNomeAndUf(String nome, Estado uf);
}
