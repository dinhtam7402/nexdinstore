package com.nexdin.nexdinstore.repository;

import com.nexdin.nexdinstore.domain.Accounts;
import com.nexdin.nexdinstore.domain.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Tokens, String> {
    Optional<Tokens> findByAccessToken(String accessToken);
    Optional<Tokens> findByRefreshToken(String refreshToken);
    List<Tokens> getAllByAccount(Accounts account);
}
