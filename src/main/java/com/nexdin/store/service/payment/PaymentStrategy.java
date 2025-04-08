package com.nexdin.store.service.payment;

import com.nexdin.store.entity.Orders;

public interface PaymentStrategy {
    void pay(Orders orders);
}
