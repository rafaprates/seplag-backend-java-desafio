package com.seplag.servidores.servidores.efetivo.mapper;

import com.seplag.servidores.compartilhado.dtos.response.PessoaResponseDTO;
import com.seplag.servidores.compartilhado.mappers.PessoaMapper;
import com.seplag.servidores.servidores.efetivo.dtos.request.AtualizarServidorEfetivoDTO;
import com.seplag.servidores.servidores.efetivo.dtos.request.CriarServidorEfetivoDTO;
import com.seplag.servidores.servidores.efetivo.dtos.response.ServidorEfetivoResponseDTO;
import com.seplag.servidores.servidores.efetivo.entities.ServidorEfetivo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServidorEfetivoMapper {

    private final PessoaMapper pessoaMapper;

    public ServidorEfetivo toEntity(CriarServidorEfetivoDTO servidorDTO) {
        return new ServidorEfetivo(
                servidorDTO.matricula(),
                pessoaMapper.toEntity(servidorDTO.pessoa())
        );
    }

    public ServidorEfetivo toEntity(AtualizarServidorEfetivoDTO servidorDTO) {
        return new ServidorEfetivo(
                servidorDTO.matricula(),
                pessoaMapper.toEntity(servidorDTO.pessoa())
        );
    }

    public ServidorEfetivoResponseDTO toDTO(ServidorEfetivo servidorEfetivo) {
        PessoaResponseDTO pessoaDTO = pessoaMapper.toDTO(servidorEfetivo);
        return new ServidorEfetivoResponseDTO(pessoaDTO, servidorEfetivo.getMatricula());
    }
}
