package com.nexdin.store.service;

import com.nexdin.store.entity.Orders;
import com.nexdin.store.payload.request.client.ClientOrderRequest;

public interface OrderService {
    Orders placeOrder(ClientOrderRequest request);
}
