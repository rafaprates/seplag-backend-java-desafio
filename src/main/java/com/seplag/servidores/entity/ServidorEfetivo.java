package com.seplag.servidores.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServidorEfetivo extends Pessoa {

    @Column(name = "se_matricula")
    private String matricula;

    public ServidorEfetivo(String matricula, Pessoa pessoa) {
        super(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getSexo(), pessoa.getMae(), pessoa.getPai(), pessoa.getEnderecos());
        this.matricula = matricula;
    }
}
