package com.nexdin.store.service;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.payload.request.ClientInfoRequest;
import com.nexdin.store.payload.response.ClientInfoResponse;

public interface CustomerService {
    Customer saveCustomerOrder(ClientInfoRequest request);
}
