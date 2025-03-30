package com.seplag.servidores.security.controllers;

import com.seplag.servidores.security.services.TokenService;
import com.seplag.servidores.security.values.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<Token> token(Authentication authentication) {
        Token token = tokenService.gerar(authentication);
        return ResponseEntity.ok(token);
    }

}
