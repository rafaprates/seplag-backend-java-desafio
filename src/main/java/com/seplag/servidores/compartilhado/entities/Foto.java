package com.seplag.servidores.compartilhado.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "foto_pessoa")
@NoArgsConstructor
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "fp_data")
    private LocalDate data;

    @Column(name = "fp_bucket")
    private String bucket;

    @Column(name = "fp_hash")
    private String hash;

    public Foto(Pessoa pessoa, String bucket, String hash) {
        this.pessoa = pessoa;
        this.bucket = bucket;
        this.hash = hash;
        this.data = LocalDate.now();
    }
}
