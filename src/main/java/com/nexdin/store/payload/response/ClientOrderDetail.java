package com.nexdin.store.payload.response;

import lombok.Data;

@Data
public class ClientOrderDetail {
    private Integer product;
    private Integer quantity;
    private Integer price;
}
