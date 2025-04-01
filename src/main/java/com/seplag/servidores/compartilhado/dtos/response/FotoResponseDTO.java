package com.seplag.servidores.compartilhado.dtos.response;

import com.seplag.servidores.compartilhado.entities.Foto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FotoResponseDTO {

    private LocalDate data;
    private String url;

    public FotoResponseDTO(Foto foto) {
        this.data = foto.getData();
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
