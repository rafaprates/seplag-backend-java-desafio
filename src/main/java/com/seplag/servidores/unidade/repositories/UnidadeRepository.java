package com.seplag.servidores.unidade.repositories;

import com.seplag.servidores.unidade.entities.Unidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

//    @Query("""
//                SELECT se
//                FROM Unidade u
//                JOIN Lotacao l ON l.unidade.id = u.id
//                JOIN Pessoa p ON p.id = l.pessoa.id
//                JOIN ServidorEfetivo se ON se.id = p.id
//                WHERE u.id = :unidadeId
//            """)
//    Page<ServidorEfetivo> findAllServidoresEfetivosByUnidadeId(@Param("unidadeId") Long unidadeId, Pageable pageable);

//    @Query(value = """
//                SELECT u.*, p.*, se.*
//                FROM lotacao l
//                JOIN unidade u ON u.unid_id = l.unid_id
//                JOIN pessoa p ON p.pes_id = l.pes_id
//                JOIN servidor_efetivo se ON se.pes_id = p.pes_id
//                WHERE u.unid_id = :unidadeId
//            """, nativeQuery = true)
//    List<Object[]> findUnidadeAndServidorEfetivoByUnidadeId(@Param("unidadeId") Long unidadeId);


}
