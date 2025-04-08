package com.seplag.servidores.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "unidade")
public class Unidade {

    @Id
    @Column(name = "unid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "unid_nome")
    private String nome;

    @NotNull
    @Column(name = "unid_sigla")
    private String sigla;

    // Set impossibilita a duplicação de endereços
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "unid_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lotacao> lotacoes = new HashSet<>();

    public Unidade(long id) {
        this.id = id;
    }

    public Unidade(long id, String nome, String sigla, Set<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.enderecos = enderecos;
    }

    public Unidade(long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Unidade(String nome, String sigla, Endereco endereco) {
        this.nome = nome;
        this.sigla = sigla;
        this.enderecos.add(endereco);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Unidade unidade = (Unidade) object;
        return id == unidade.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
