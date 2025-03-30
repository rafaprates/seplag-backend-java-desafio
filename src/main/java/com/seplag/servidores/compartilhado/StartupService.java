package com.seplag.servidores.compartilhado;

import com.seplag.servidores.security.entities.Role;
import com.seplag.servidores.security.entities.Usuario;
import com.seplag.servidores.security.services.RoleService;
import com.seplag.servidores.security.services.UserDetailsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartupService {

    private final UserDetailsService userDetailsService;
    private final RoleService roleService;

    @PostConstruct
    public void onStartup() {
        log.info("Rodando rotina de inicialização do sistema");

        Role rolePadrao = new Role("ROLE_USER");
        roleService.create(rolePadrao);

        Usuario usuarioPadrao = new Usuario(
                "seplag",
                "8xON{d]7;B4i",
                Set.of(rolePadrao)
        );

        userDetailsService.create(usuarioPadrao);
    }

}
