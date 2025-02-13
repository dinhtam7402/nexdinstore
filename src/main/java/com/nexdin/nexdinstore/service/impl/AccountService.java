package com.nexdin.nexdinstore.service.impl;

import com.nexdin.nexdinstore.domain.Accounts;
import com.nexdin.nexdinstore.domain.Roles;
import com.nexdin.nexdinstore.domain.enums.ERole;
import com.nexdin.nexdinstore.repository.IAccountRepository;
import com.nexdin.nexdinstore.repository.IRoleRepository;
import com.nexdin.nexdinstore.service.IAccountService;
import com.nexdin.nexdinstore.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByUsername(String username) {
        boolean exists = accountRepository.existsByUsername(username);
        log.info("Username '{}' exists: {}", username, exists);
        return exists;
    }

    @Override
    public Accounts createAccount(String username, String encryptedPassword) {
        Accounts account = new Accounts();

        String generatedID;
        do {
            generatedID = IDGenerator.generateID();
        } while (accountRepository.existsById(generatedID));

        account.setAccountID(generatedID);
        log.debug("Generated unique account ID: {}", generatedID);
        account.setUsername(username);
        account.setPassword(encryptedPassword);

        Set<Roles> authorities = new HashSet<>();
        authorities.add(roleRepository.findByName(ERole.ROLE_ADMIN));
        account.setAuthorities(authorities);

        Accounts saveAccount = accountRepository.save(account);
        log.info("Account with username '{}' saved successfully", saveAccount);

        return saveAccount;
    }
}
