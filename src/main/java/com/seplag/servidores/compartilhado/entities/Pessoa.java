package com.seplag.servidores.compartilhado.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    protected Long id;

    @Column(name = "pes_nome")
    protected String nome;

    @Column(name = "pes_data_nascimento")
    protected LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "pes_sexo")
    protected Sexo sexo;

    @Column(name = "pes_mae")
    protected String mae;

    @Column(name = "pes_pai")
    protected String pai;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    protected Set<Endereco> enderecos;

    @OneToMany
    @JoinColumn(name = "pes_id")
    protected Set<Foto> fotos;

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(String nome, LocalDate dataNascimento, Sexo pes_sexo, String mae, String pai, Set<Endereco> enderecos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = pes_sexo;
        this.mae = mae;
        this.pai = pai;
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
