package com.seplag.servidores.compartilhado.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cidade")
@NoArgsConstructor
public class Cidade {

    @Id
    @Column(name = "cid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cid_nome")
    private String nome;

    @Column(name = "cid_uf")
    @Enumerated(EnumType.STRING)
    private Estado uf;

    public Cidade(Long id) {
        this.id = id;
    }

    public Cidade(String nome, Estado uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade(Long id, String nome, Estado uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public boolean hasId() {
        return id != null;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Cidade cidade = (Cidade) object;
        return id == cidade.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
