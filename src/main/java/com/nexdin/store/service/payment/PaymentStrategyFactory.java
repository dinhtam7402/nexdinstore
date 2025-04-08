package com.nexdin.store.service.payment;

import com.nexdin.store.entity.enums.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {
    private final Map<String, PaymentStrategy> strategyMap;

    public PaymentStrategy getStrategy(PaymentType type) {
        PaymentStrategy strategy = strategyMap.get(type.name());

        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + type);
        }

        return strategy;
    }
}
