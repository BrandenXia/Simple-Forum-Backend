package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Permission;
import com.simpleforum.simpleforum.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

    Role findByNameContainingIgnoreCase(String name);

    Boolean existsByName(String name);

    Boolean existsByPermissions(Set<Permission> permissions);
}
