package com.seplag.servidores.servidores.temporario.entities;

import com.seplag.servidores.compartilhado.entities.Pessoa;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServidorTemporario extends Pessoa {

    @Column(name = "st_data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate dataDemissao;

    public ServidorTemporario(Pessoa pessoa, LocalDate dataAdmissao, LocalDate dataDemissao) {
        super(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getSexo(), pessoa.getMae(), pessoa.getPai(), pessoa.getEnderecos());
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
    }
}
