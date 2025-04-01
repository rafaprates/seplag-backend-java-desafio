package com.seplag.servidores.security.controllers;

import com.seplag.servidores.security.dto.RefreshTokenRequest;
import com.seplag.servidores.security.dto.RefreshTokenResponseDTO;
import com.seplag.servidores.security.services.TokenService;
import com.seplag.servidores.security.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<LoginResponseDTO> token(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = tokenService.gerarToken(principal);
        String refreshToken = tokenService.gerarRefreshToken(principal);

        return ResponseEntity.ok(new LoginResponseDTO(token, refreshToken));
    }

    @PostMapping("/api/v1/auth/refresh")
    public ResponseEntity<RefreshTokenResponseDTO> refresh(@RequestBody RefreshTokenRequest request) {
        String novoToken = tokenService.refresh(request.refreshToken());
        return ResponseEntity.ok(new RefreshTokenResponseDTO(novoToken));
    }

}
