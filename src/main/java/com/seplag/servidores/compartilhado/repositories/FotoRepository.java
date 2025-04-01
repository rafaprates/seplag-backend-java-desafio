package com.seplag.servidores.compartilhado.repositories;

import com.seplag.servidores.compartilhado.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    void deleteByPessoaId(Long id);

}
