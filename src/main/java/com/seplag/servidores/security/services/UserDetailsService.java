package com.seplag.servidores.security.services;

import com.seplag.servidores.security.entities.Role;
import com.seplag.servidores.security.entities.Usuario;
import com.seplag.servidores.security.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public void create(UserDetails user) {
        log.info("Criando usuário: {}", user.getUsername());

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        Usuario u = new Usuario(
                user.getUsername(),
                encodedPassword,
                (Set<Role>) user.getAuthorities()
        );

        usuarioRepository.save(u);
    }
}