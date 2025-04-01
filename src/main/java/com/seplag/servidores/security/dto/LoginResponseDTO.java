package com.seplag.servidores.security.dto;

public record LoginResponseDTO(
        String token,
        String refreshToken
) { }
