package com.nexdin.nexdinstore.controller;

import com.nexdin.nexdinstore.domain.Accounts;
import com.nexdin.nexdinstore.dto.request.AuthRequest;
import com.nexdin.nexdinstore.dto.request.TokenRequest;
import com.nexdin.nexdinstore.dto.response.AuthResponse;
import com.nexdin.nexdinstore.dto.response.Response;
import com.nexdin.nexdinstore.service.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<?>> register(@RequestBody AuthRequest request) {
        Accounts account = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("Register Successfully")
                        .result(account)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Response<?>> login(@RequestBody AuthRequest request, HttpServletRequest httpRequest) {
        AuthResponse body = authService.login(request, httpRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Login Successfully")
                        .result(body)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Response<?>> refreshToken(@RequestBody TokenRequest request, HttpServletRequest httpRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Refresh Token Successfully")
                        .result(authService.refreshToken(request, httpRequest))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/logout")
    public ResponseEntity<Response<?>> logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken.replace("Bearer ", ""));
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Logout Successfully")
                        .result(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/revoked-token")
    public ResponseEntity<Response<?>> revokedToken(@RequestBody TokenRequest request) {
        authService.revokedToken(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Revoked Token Successfully")
                        .result(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
