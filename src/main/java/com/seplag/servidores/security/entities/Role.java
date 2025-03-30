package com.seplag.servidores.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id // Chave primária natural
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}