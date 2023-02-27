package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    Permission findByName(String name);

    Boolean existsByName(String name);
}
