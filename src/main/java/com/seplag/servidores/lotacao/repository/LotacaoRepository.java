package com.seplag.servidores.lotacao.repository;

import com.seplag.servidores.lotacao.entity.Lotacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {

    Page<Lotacao> findByPessoaNomeContainingIgnoreCase(String nomeServidor, Pageable pageable);
}
