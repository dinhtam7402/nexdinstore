package com.nexdin.nexdinstore.service;

import com.nexdin.nexdinstore.domain.Accounts;
import com.nexdin.nexdinstore.dto.request.AuthRequest;
import com.nexdin.nexdinstore.dto.request.TokenRequest;
import com.nexdin.nexdinstore.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthService {
    Accounts register(AuthRequest request);
    AuthResponse login(AuthRequest request, HttpServletRequest httpRequest);
    AuthResponse refreshToken(TokenRequest refreshToken, HttpServletRequest httpRequest);
    void revokedToken(TokenRequest request);
    void logout(String accessToken);
}
