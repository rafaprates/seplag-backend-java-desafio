package com.seplag.servidores.servidores.efetivo.controller;

import com.seplag.servidores.compartilhado.dtos.response.FotoResponseDTO;
import com.seplag.servidores.compartilhado.dtos.response.RecursoCriadoDTO;
import com.seplag.servidores.compartilhado.entities.Foto;
import com.seplag.servidores.compartilhado.mappers.FotoMapper;
import com.seplag.servidores.compartilhado.services.PessoaService;
import com.seplag.servidores.servidores.efetivo.dtos.request.AtualizarServidorEfetivoDTO;
import com.seplag.servidores.servidores.efetivo.dtos.request.CriarServidorEfetivoDTO;
import com.seplag.servidores.servidores.efetivo.dtos.response.ServidorEfetivoResponseDTO;
import com.seplag.servidores.servidores.efetivo.entities.ServidorEfetivo;
import com.seplag.servidores.servidores.efetivo.mapper.ServidorEfetivoMapper;
import com.seplag.servidores.servidores.efetivo.services.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/servidores-efetivos")
@Tag(name = "Servidores Efetivos", description = "CRUD completo para Servidores Efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;
    private final PessoaService pessoaService;
    private final FotoMapper fotoMapper;
    private final ServidorEfetivoMapper servidorEfetivoMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> registrarServidorEfetivo(@RequestBody CriarServidorEfetivoDTO request) {
        ServidorEfetivo entity = servidorEfetivoMapper.toEntity(request);
        ServidorEfetivo servidorEfetivo = servidorEfetivoService.registrarServidorEfetivo(entity);
        return ResponseEntity.ok(new RecursoCriadoDTO(servidorEfetivo.getId()));
    }

    @PostMapping("{id}/fotos")
    public ResponseEntity<FotoResponseDTO> adicionarFoto(@PathVariable Long id, @RequestParam MultipartFile foto) {
        Foto ft = pessoaService.adicionarFoto(id, foto);
        return ResponseEntity.ok(fotoMapper.toDTO(ft));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorEfetivoResponseDTO>> buscarTodos(Pageable pageable) {
        Page<ServidorEfetivoResponseDTO> response = servidorEfetivoService
                .buscarTodos(pageable)
                .map(servidorEfetivoMapper::toDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> buscarPorId(@PathVariable Long id) {
        ServidorEfetivo servidorEfetivo = servidorEfetivoService.buscarPorId(id);
        return ResponseEntity.ok(servidorEfetivoMapper.toDTO(servidorEfetivo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> atualizarServidorEfetivo(
            @PathVariable Long id,
            @RequestBody AtualizarServidorEfetivoDTO request
    ) {
        ServidorEfetivo servidorTemporarioEntity = servidorEfetivoMapper.toEntity(request);
        ServidorEfetivo servidorEfetivo = servidorEfetivoService.atualizarPorId(id, servidorTemporarioEntity);
        return ResponseEntity.ok(servidorEfetivoMapper.toDTO(servidorEfetivo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServidorEfetivo(@PathVariable Long id) {
        servidorEfetivoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
