package com.seplag.servidores.repository;

import com.seplag.servidores.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    void deleteByPessoaId(Long id);

}
