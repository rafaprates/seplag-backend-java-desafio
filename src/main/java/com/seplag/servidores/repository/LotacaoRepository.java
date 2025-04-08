package com.seplag.servidores.repository;

import com.seplag.servidores.entity.Lotacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {

    Page<Lotacao> findByPessoaNomeContainingIgnoreCase(String nomeServidor, Pageable pageable);
}
