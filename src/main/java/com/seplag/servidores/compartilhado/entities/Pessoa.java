package com.seplag.servidores.compartilhado.entities;

import com.seplag.servidores.lotacao.entity.Lotacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Foto> fotos;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lotacao> lotacoes = new HashSet<>();

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(String nome, LocalDate dataNascimento, Sexo sexo, String mae, String pai) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.mae = mae;
        this.pai = pai;
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
