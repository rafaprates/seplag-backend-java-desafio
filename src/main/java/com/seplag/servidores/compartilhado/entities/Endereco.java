package com.seplag.servidores.compartilhado.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "endereco")
@NoArgsConstructor
public class Endereco {

    @Id
    @Column(name = "end_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_tipo_logradouro")
    @Enumerated(EnumType.STRING)
    private TipoLogradouro tipoLogradouro;

    @Column(name = "end_logradouro")
    private String logradouro;

    @Positive
    @Column(name = "end_numero")
    private int numero;

    @Column(name = "end_bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private Cidade cidade;

    public Endereco(TipoLogradouro tipoLogradouro, String logradouro, int numero, String bairro, Cidade cidade) {
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public boolean hasId() {
        return id != null;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Endereco endereco = (Endereco) object;
        return id == endereco.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
