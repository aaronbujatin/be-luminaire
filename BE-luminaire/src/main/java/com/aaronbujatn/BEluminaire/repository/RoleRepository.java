package com.aaronbujatn.BEluminaire.repository;

import com.aaronbujatn.BEluminaire.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
