package com.nexdin.nexdinstore.repository;

import com.nexdin.nexdinstore.domain.Roles;
import com.nexdin.nexdinstore.domain.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Roles, String> {
    Roles findByName(ERole name);
}
