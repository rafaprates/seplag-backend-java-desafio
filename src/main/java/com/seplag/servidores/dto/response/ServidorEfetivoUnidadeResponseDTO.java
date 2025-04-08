package com.seplag.servidores.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ServidorEfetivoUnidadeResponseDTO {

    private String nome;
    private Integer idade;
    private UnidadeResponseDTO unidade;
    private Set<FotoResponseDTO> fotos = new HashSet<>();

    public ServidorEfetivoUnidadeResponseDTO(String nome, LocalDate dataNascimento, UnidadeResponseDTO unidade) {
        this.nome = nome;
        this.unidade = unidade;
        this.idade = calcularIdade(dataNascimento);
    }

    public void addFoto(FotoResponseDTO foto) {
        this.fotos.add(foto);
    }

    private int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return hoje.getYear() - dataNascimento.getYear() - (hoje.getDayOfYear() < dataNascimento.getDayOfYear() ? 1 : 0);
    }

}
