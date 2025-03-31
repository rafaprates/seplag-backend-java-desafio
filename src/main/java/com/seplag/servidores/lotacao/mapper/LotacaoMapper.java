package com.seplag.servidores.lotacao.mapper;

import com.seplag.servidores.compartilhado.entities.Pessoa;
import com.seplag.servidores.compartilhado.mappers.PessoaMapper;
import com.seplag.servidores.lotacao.dtos.request.AtualizarLotacaoDTO;
import com.seplag.servidores.lotacao.dtos.request.CriarLotacaoDTO;
import com.seplag.servidores.lotacao.dtos.response.LotacaoResponseDTO;
import com.seplag.servidores.lotacao.entity.Lotacao;
import com.seplag.servidores.unidade.entities.Unidade;
import com.seplag.servidores.unidade.mapper.UnidadeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotacaoMapper {

    private final PessoaMapper pessoaMapper;
    private final UnidadeMapper unidadeMapper;

    public Lotacao toEntity(CriarLotacaoDTO dto) {
        Pessoa pessoa = new Pessoa(dto.getPessoaId());

        Unidade unidade = new Unidade(dto.getUnidadeId());

        return new Lotacao(
                pessoa,
                unidade,
                dto.getDataLotacao(),
                dto.getDataRemocao(),
                dto.getPortaria()
        );
    }

    public Lotacao toEntity(AtualizarLotacaoDTO dto) {
        Pessoa pessoa = new Pessoa(dto.getPessoaId());

        Unidade unidade = new Unidade(dto.getUnidadeId());

        return new Lotacao(
                pessoa,
                unidade,
                dto.getDataLotacao(),
                dto.getDataRemocao(),
                dto.getPortaria()
        );
    }

    public LotacaoResponseDTO toDTO(Lotacao lotacao) {
        return new LotacaoResponseDTO(
                lotacao.getId(),
                pessoaMapper.toDTO(lotacao.getPessoa()),
                unidadeMapper.toDTO(lotacao.getUnidade()),
                lotacao.getDataLotacao(),
                lotacao.getDataRemocao(),
                lotacao.getPortaria()
        );
    }
}