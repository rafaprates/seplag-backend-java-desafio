package com.seplag.servidores.compartilhado.mappers;

import com.seplag.servidores.compartilhado.dtos.response.FotoResponseDTO;
import com.seplag.servidores.compartilhado.entities.Foto;
import com.seplag.servidores.compartilhado.services.MinioService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FotoMapper {

    private final MinioService minioService;

    @SneakyThrows
    public FotoResponseDTO toDTO(Foto foto) {
        String url = minioService.urlFoto(foto.getBucket(), foto.getHash());
        FotoResponseDTO fotoResponse = new FotoResponseDTO(foto);
        fotoResponse.setUrl(url);

        return fotoResponse;
    }

}
