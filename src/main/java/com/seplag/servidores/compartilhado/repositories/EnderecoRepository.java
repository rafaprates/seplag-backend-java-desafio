package com.seplag.servidores.compartilhado.repositories;

import com.seplag.servidores.compartilhado.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
