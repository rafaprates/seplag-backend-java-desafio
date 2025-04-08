package com.seplag.servidores.mapper;

import com.seplag.servidores.dto.response.PessoaResponseDTO;
import com.seplag.servidores.dto.request.AtualizarServidorEfetivoDTO;
import com.seplag.servidores.dto.request.CriarServidorEfetivoDTO;
import com.seplag.servidores.dto.response.ServidorEfetivoResponseDTO;
import com.seplag.servidores.entity.ServidorEfetivo;
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
