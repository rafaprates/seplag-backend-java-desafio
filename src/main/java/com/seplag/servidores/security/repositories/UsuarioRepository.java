package com.seplag.servidores.security.repositories;

import com.seplag.servidores.security.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}