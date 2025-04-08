package com.nexdin.store.payload.response.client;

import lombok.Data;

@Data
public class ClientOrderDetailResponse {
    private Integer product;
    private Integer quantity;
    private Integer price;
}
