package com.nexdin.store.service;

import com.nexdin.store.payload.request.ClientOrderRequest;
import com.nexdin.store.payload.response.ClientOrderResponse;

public interface OrderService {
    ClientOrderResponse placeOrder(ClientOrderRequest request);
}
