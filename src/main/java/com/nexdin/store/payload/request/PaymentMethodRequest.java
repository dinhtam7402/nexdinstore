package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class PaymentMethodRequest {
    private String type;
    private Integer providerId;
    private Integer userId;
    private String account;
    private String expiry;
    private Boolean isDefault;
}
