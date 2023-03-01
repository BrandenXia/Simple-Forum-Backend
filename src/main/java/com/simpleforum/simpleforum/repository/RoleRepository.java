package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

    Role findByNameContainingIgnoreCase(String name);

    Boolean existsByName(String name);
}
