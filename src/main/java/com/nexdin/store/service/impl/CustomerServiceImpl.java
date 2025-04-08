package com.nexdin.store.service.impl;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.mapper.client.ClientInfoMapper;
import com.nexdin.store.payload.request.client.ClientInfoRequest;
import com.nexdin.store.repository.CustomerRepository;
import com.nexdin.store.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ClientInfoMapper mapper;

    @Override
    public Customer createGuestCustomer(ClientInfoRequest request) {
        return customerRepository.save(mapper.requestToEntity(request));
    }
}
