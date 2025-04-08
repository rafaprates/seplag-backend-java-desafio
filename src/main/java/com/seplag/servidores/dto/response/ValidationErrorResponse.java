package com.seplag.servidores.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(
    int status,
    String message,
    LocalDateTime timestamp,
    String path,
    Map<String, String> errors
) { }