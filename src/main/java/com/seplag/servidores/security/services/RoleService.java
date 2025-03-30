package com.seplag.servidores.security.services;

import com.seplag.servidores.security.entities.Role;
import com.seplag.servidores.security.repositories.RoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void create(@Valid Role role) {
        log.info("Criando nova autoridade: {}", role);
        roleRepository.save(role);
    }

}
