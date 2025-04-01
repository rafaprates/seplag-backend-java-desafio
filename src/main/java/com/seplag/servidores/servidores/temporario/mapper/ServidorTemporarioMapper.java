package com.seplag.servidores.servidores.temporario.mapper;

import com.seplag.servidores.compartilhado.mappers.PessoaMapper;
import com.seplag.servidores.servidores.temporario.dtos.request.AtualizarServidorTemporarioDTO;
import com.seplag.servidores.servidores.temporario.dtos.request.CriarServidorTemporarioDTO;
import com.seplag.servidores.servidores.temporario.dtos.response.ServidorTemporarioResponseDTO;
import com.seplag.servidores.servidores.temporario.entities.ServidorTemporario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServidorTemporarioMapper {

    private final PessoaMapper pessoaMapper;

    public ServidorTemporario toEntity(CriarServidorTemporarioDTO servidorDTO) {
        return new ServidorTemporario(
                pessoaMapper.toEntity(servidorDTO.pessoa()),
                servidorDTO.dataAdmissao(),
                servidorDTO.dataDemissao()
        );
    }

    public ServidorTemporario toEntity(AtualizarServidorTemporarioDTO servidorDTO) {
        return new ServidorTemporario(
                pessoaMapper.toEntity(servidorDTO.pessoa()),
                servidorDTO.dataAdmissao(),
                servidorDTO.dataDemissao()
        );
    }

    public ServidorTemporarioResponseDTO toDTO(ServidorTemporario servidorTemporario) {
        return new ServidorTemporarioResponseDTO(
                pessoaMapper.toDTO(servidorTemporario),
                servidorTemporario.getDataAdmissao(),
                servidorTemporario.getDataDemissao()
        );
    }

}
