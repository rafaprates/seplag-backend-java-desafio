package com.seplag.servidores.service;

import com.seplag.servidores.entity.Role;
import com.seplag.servidores.repository.RoleRepository;
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
