package com.seplag.servidores.service;

import com.seplag.servidores.entity.RefreshToken;
import com.seplag.servidores.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.expiration-time-in-minutes}")
    private int expirationTimeInMinutes;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    // Duração curta
    public String gerarToken(UserDetails userDetails) {
        Instant now = Instant.now();

        String scope = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expirationTimeInMinutes, ChronoUnit.MINUTES))
                .subject(userDetails.getUsername())
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    // Duração maior, de 12h (tempo da jornada média de um servidor)
    public String gerarRefreshToken(UserDetails userDetails) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(12, ChronoUnit.HOURS))
                .subject(userDetails.getUsername())
                .build();

        String tokenValue = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        refreshTokenRepository.save(new RefreshToken(tokenValue, userDetails.getUsername()));

        return tokenValue;
    }

    public String refresh(String refreshToken) {
        RefreshToken token = refreshTokenRepository
                .findById(refreshToken)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Refresh token não encontrado"));

        if (!estaValido(refreshToken))
            throw new AuthenticationCredentialsNotFoundException("Refresh token expirado");

        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUsername());

        return gerarToken(userDetails);
    }

    private boolean estaValido(String token) {
        try {
            Jwt jwt = decoder.decode(token);
            return jwt.getExpiresAt() != null && jwt.getExpiresAt().isAfter(Instant.now());
        } catch (JwtException e) {
            return false;
        }
    }

}
