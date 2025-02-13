package com.nexdin.nexdinstore.utils;

import com.nexdin.nexdinstore.domain.Roles;
import com.nexdin.nexdinstore.domain.enums.ERole;
import com.nexdin.nexdinstore.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder {
    @Autowired
    private IRoleRepository repository;

    @EventListener
    @Transactional
    public void loadRoles(ContextRefreshedEvent event) {
        List<ERole> roles = Arrays.stream(ERole.values()).toList();

        for (ERole role : roles) {
            if (repository.findByName(role) == null) {
                repository.save(new Roles(IDGenerator.generateID(), role));
            }
        }
    }
}
