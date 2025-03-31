package com.seplag.servidores.compartilhado.repositories;

import com.seplag.servidores.compartilhado.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
