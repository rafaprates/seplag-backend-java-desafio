package com.seplag.servidores.security.repositories;

import com.seplag.servidores.security.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
