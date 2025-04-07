package com.nexdin.store.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientOrderResponse {
    private Integer id;
    private ClientInfoResponse customer;
    private Integer totalPrice;
    private List<ClientOrderDetail> details;
}
