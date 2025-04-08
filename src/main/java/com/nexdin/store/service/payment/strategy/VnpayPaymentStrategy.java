package com.nexdin.store.service.payment.strategy;

import com.nexdin.store.entity.Orders;
import com.nexdin.store.entity.enums.OrderStatus;
import com.nexdin.store.service.payment.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component("VNPAY")
public class VnpayPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Orders orders) {
        orders.setStatus(OrderStatus.PAID);
    }
}
