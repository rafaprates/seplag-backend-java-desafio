package com.seplag.servidores.compartilhado.services;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final HashService hashService;

    public String subirImagem(MultipartFile imagem, String nomeBucket) throws Exception {
        String nomeArquivo = hashService.sha256(imagem);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(nomeBucket)
                        .object(nomeArquivo)
                        .stream(imagem.getInputStream(), imagem.getSize(), -1)
                        .contentType(imagem.getContentType())
                        .build()
        );

        return nomeArquivo;
    }

    public String urlFoto(String bucketName, String objectName) throws Exception {
        if (bucketName == null) return null;
        if (objectName == null) return null;

        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(5 * 60) // 5 minutos
                        .build()
        );
    }

}
