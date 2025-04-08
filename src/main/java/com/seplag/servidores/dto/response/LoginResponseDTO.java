package com.seplag.servidores.dto.response;

public record LoginResponseDTO(
        String token,
        String refreshToken
) { }
