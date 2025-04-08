package com.seplag.servidores.security;

import com.seplag.servidores.entity.Usuario;
import com.seplag.servidores.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        Usuario usuario = (Usuario) userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, usuario.getPassword())) {
            UsernamePasswordAuthenticationToken auth = UsernamePasswordAuthenticationToken.authenticated(usuario, password, usuario.getAuthorities());
            return auth;
        }

        log.info("Falhou ao autenticar usuário");
        throw new BadCredentialsException("Usuário e/ou senha não inválidos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
