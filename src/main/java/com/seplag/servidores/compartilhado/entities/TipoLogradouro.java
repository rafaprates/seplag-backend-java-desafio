package com.seplag.servidores.compartilhado.entities;

public enum TipoLogradouro {
    RUA("Rua"),
    AVENIDA("Avenida"),
    TRAVESSA("Travessa"),
    ALAMEDA("Alameda"),
    PRACA("Praça"),
    RODOVIA("Rodovia"),
    ESTRADA("Estrada"),
    VIADUTO("Viaduto"),
    LARGO("Largo"),
    VIELA("Viela"),
    PASSAGEM("Passagem");

    private final String descricao;

    TipoLogradouro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
