package com.seplag.servidores.controller;

import com.seplag.servidores.dto.response.FotoResponseDTO;
import com.seplag.servidores.dto.response.RecursoCriadoDTO;
import com.seplag.servidores.entity.Foto;
import com.seplag.servidores.mapper.FotoMapper;
import com.seplag.servidores.service.PessoaService;
import com.seplag.servidores.dto.request.AtualizarServidorTemporarioDTO;
import com.seplag.servidores.dto.request.CriarServidorTemporarioDTO;
import com.seplag.servidores.dto.response.ServidorTemporarioResponseDTO;
import com.seplag.servidores.entity.ServidorTemporario;
import com.seplag.servidores.mapper.ServidorTemporarioMapper;
import com.seplag.servidores.service.ServidorTemporarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/servidores-temporarios")
@Tag(name = "Servidores Temporários", description = "CRUD completo para Servidores Temporários")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;
    private final PessoaService pessoaService;
    private final ServidorTemporarioMapper servidorTemporarioMapper;
    private final FotoMapper fotoMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> registrarServidorTemporario(@RequestBody CriarServidorTemporarioDTO request) {
        ServidorTemporario servidorTemporarioEntity = servidorTemporarioMapper.toEntity(request);
        ServidorTemporario servidorTemporario = servidorTemporarioService.registrarServidorTemporario(servidorTemporarioEntity);
        return ResponseEntity.ok(new RecursoCriadoDTO(servidorTemporario.getId()));
    }

    @PostMapping("{id}/fotos")
    public ResponseEntity<FotoResponseDTO> adicionarFoto(@PathVariable Long id, @RequestParam MultipartFile foto) {
        Foto ft = pessoaService.adicionarFoto(id, foto);
        return ResponseEntity.ok(fotoMapper.toDTO(ft));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorTemporarioResponseDTO>> buscarTodos(Pageable pageable) {
        Page<ServidorTemporarioResponseDTO> response = servidorTemporarioService
                .buscarTodos(pageable)
                .map(servidorTemporarioMapper::toDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> buscarPorId(@PathVariable Long id) {
        ServidorTemporario servidorTemporario = servidorTemporarioService.buscarPorId(id);
        return ResponseEntity.ok(servidorTemporarioMapper.toDTO(servidorTemporario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> atualizarServidorTemporario(
            @PathVariable Long id,
            @RequestBody AtualizarServidorTemporarioDTO request
    ) {
        ServidorTemporario servidorTemporarioEntity = servidorTemporarioMapper.toEntity(request);
        ServidorTemporario servidorTemporario = servidorTemporarioService.atualizarPorId(id, servidorTemporarioEntity);
        return ResponseEntity.ok(servidorTemporarioMapper.toDTO(servidorTemporario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServidorTemporario(@PathVariable Long id) {
        servidorTemporarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
