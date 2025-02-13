package com.nexdin.nexdinstore.service;

import com.nexdin.nexdinstore.domain.Accounts;

public interface IAccountService {
    boolean existsByUsername(String username);
    Accounts createAccount(String username, String encryptedPassword);
}
