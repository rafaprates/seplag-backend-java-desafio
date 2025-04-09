package com.seplag.servidores.repository;

import com.seplag.servidores.dto.response.ServidorEfetivoUnidadeResponseDTO;
import com.seplag.servidores.mapper.ServidorEfetivoUnidadeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UnidadeJdbcRepository {

    private final JdbcClient jdbcClient;
    private final ServidorEfetivoUnidadeMapper servidorEfetivoUnidadeMapper;

    public Page<ServidorEfetivoUnidadeResponseDTO> buscarServidoresEfetivosPorUnidadeId(Long id, Pageable pageable) {
        final String sql = """
                SELECT
                  u.*, p.*, fp.*
                FROM lotacao l
                INNER JOIN unidade u ON u.unid_id = l.unid_id
                INNER JOIN servidor_efetivo se ON se.pes_id = l.pes_id
                INNER JOIN pessoa p ON p.pes_id = se.pes_id
                LEFT JOIN foto_pessoa fp ON fp.pes_id = se.pes_id
                WHERE u.unid_id = :unidadeId
                OFFSET :offset ROWS FETCH NEXT :pageSize ROWS ONLY
                """;

        List<ServidorEfetivoUnidadeResponseDTO> correspondencias = jdbcClient
                .sql(sql)
                .param("unidadeId", id)
                .param("offset", pageable.getOffset())
                .param("pageSize", pageable.getPageSize())
                .query(servidorEfetivoUnidadeMapper);

        final String countSql = """
                SELECT 
                    COUNT(1)
                FROM lotacao l
                INNER JOIN unidade u ON u.unid_id = l.unid_id
                INNER JOIN servidor_efetivo se ON se.pes_id = l.pes_id
                INNER JOIN pessoa p ON p.pes_id = se.pes_id
                LEFT JOIN foto_pessoa fp ON fp.pes_id = se.pes_id
                WHERE u.unid_id = :unidadeId
                """;

        Long total = jdbcClient
                .sql(countSql)
                .param("unidadeId", id)
                .query(Long.class)
                .optional()
                .orElse(0L);

        return new PageImpl<>(correspondencias, pageable, total);
    }

}
