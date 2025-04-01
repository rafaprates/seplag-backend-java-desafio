package com.seplag.servidores.unidade.mapper;

import com.seplag.servidores.compartilhado.entities.Foto;
import com.seplag.servidores.compartilhado.mappers.FotoMapper;
import com.seplag.servidores.unidade.dtos.responses.ServidorEfetivoUnidadeResponseDTO;
import com.seplag.servidores.unidade.entities.Unidade;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServidorEfetivoUnidadeMapper implements ResultSetExtractor<List<ServidorEfetivoUnidadeResponseDTO>> {

    private final FotoMapper fotoMapper;
    private final UnidadeMapper unidadeMapper;

    private HashMap<Long, ServidorEfetivoUnidadeResponseDTO> porServidorId = new HashMap<>();

    @Override
    public List<ServidorEfetivoUnidadeResponseDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

        while (rs.next()) {
            Long servidorId = rs.getLong("pes_id");
            String nomeServidor = rs.getString("pes_nome");
            LocalDate dataNascimentoServidor = toLocalDateOrNull(rs.getDate("pes_data_nascimento"));

            Unidade unidade = new Unidade(
                    rs.getLong("unid_id"),
                    rs.getString("unid_nome"),
                    rs.getString("unid_sigla")
            );

            Foto foto = new Foto(
                    toLocalDateOrNull(rs.getDate("fp_data")),
                    rs.getString("fp_bucket"),
                    rs.getString("fp_hash")
            );

            ServidorEfetivoUnidadeResponseDTO dto = new ServidorEfetivoUnidadeResponseDTO(
                    nomeServidor,
                    dataNascimentoServidor,
                    unidadeMapper.toDTO(unidade)
            );

            porServidorId.putIfAbsent(servidorId, dto);

            dto = porServidorId.get(servidorId);

            if (foto.arquivoExiste())
                dto.addFoto(fotoMapper.toDTO(foto));
        }


        List<ServidorEfetivoUnidadeResponseDTO> resultados = porServidorId
                .values()
                .stream()
                .toList();

        porServidorId.clear();

        return resultados;
    }

    private LocalDate toLocalDateOrNull(Date date) {
        if (date != null) return date.toLocalDate();
        return null;
    }
}
