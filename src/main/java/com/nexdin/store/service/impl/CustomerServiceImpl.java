package com.nexdin.store.service.impl;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.mapper.ClientInfoMapper;
import com.nexdin.store.payload.request.ClientInfoRequest;
import com.nexdin.store.payload.response.ClientInfoResponse;
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
    public Customer saveCustomerOrder(ClientInfoRequest request) {
        return customerRepository.save(mapper.requestToEntity(request));
    }
}
