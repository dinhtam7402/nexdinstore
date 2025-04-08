package com.nexdin.store.payload.response.client;

import com.nexdin.store.entity.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientOrderResponse {
    private Integer id;
    private ClientInfoResponse customer;
    private PaymentType type;
    private Integer totalPrice;
    private List<ClientOrderDetailResponse> orderDetails;
}
