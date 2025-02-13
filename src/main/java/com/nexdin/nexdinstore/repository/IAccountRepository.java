package com.nexdin.nexdinstore.repository;

import com.nexdin.nexdinstore.domain.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Accounts, String> {
    Boolean existsByUsername(String username);
    Optional<Accounts> findByUsername(String username);
}
