package com.seplag.servidores.mapper;

import com.seplag.servidores.dto.response.FotoResponseDTO;
import com.seplag.servidores.entity.Foto;
import com.seplag.servidores.service.MinioService;
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
