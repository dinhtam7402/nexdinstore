package com.nexdin.store.service;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.payload.request.client.ClientInfoRequest;

public interface CustomerService {
    Customer createGuestCustomer(ClientInfoRequest request);
}
