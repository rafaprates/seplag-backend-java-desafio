package com.seplag.servidores.security.repositories;

import com.seplag.servidores.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

}
