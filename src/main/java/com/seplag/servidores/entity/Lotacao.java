package com.seplag.servidores.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private Unidade unidade;

    @Column(name = "lot_data_lotacao")
    private LocalDate dataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate dataRemocao;

    @Column(name = "lot_portaria")
    private String portaria;

    public Lotacao(Pessoa pessoa, Unidade unidade, LocalDate dataLotacao, LocalDate dataRemocao, String portaria) {
        this.pessoa = pessoa;
        this.unidade = unidade;
        this.dataLotacao = dataLotacao;
        this.dataRemocao = dataRemocao;
        this.portaria = portaria;
    }

    public Lotacao(Long id, Pessoa pessoa, Unidade unidade, LocalDate dataLotacao, LocalDate dataRemocao, String portaria) {
        this.id = id;
        this.pessoa = pessoa;
        this.unidade = unidade;
        this.dataLotacao = dataLotacao;
        this.dataRemocao = dataRemocao;
        this.portaria = portaria;
    }
}
