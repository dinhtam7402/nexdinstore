package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class ProductVariantRequest {
    private String productId;
    private String size;
    private String color;
    private Integer quantity;
}
