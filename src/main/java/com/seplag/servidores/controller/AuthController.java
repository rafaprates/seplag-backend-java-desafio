package com.seplag.servidores.controller;

import com.seplag.servidores.dto.request.RefreshTokenRequest;
import com.seplag.servidores.dto.response.LoginResponseDTO;
import com.seplag.servidores.dto.response.RefreshTokenResponseDTO;
import com.seplag.servidores.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Emite tokens de autenticação, bem como permite sua renovação")
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> token(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = tokenService.gerarToken(principal);
        String refreshToken = tokenService.gerarRefreshToken(principal);

        return ResponseEntity.ok(new LoginResponseDTO(token, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDTO> refresh(@RequestBody RefreshTokenRequest request) {
        String novoToken = tokenService.refresh(request.refreshToken());
        return ResponseEntity.ok(new RefreshTokenResponseDTO(novoToken));
    }

}
