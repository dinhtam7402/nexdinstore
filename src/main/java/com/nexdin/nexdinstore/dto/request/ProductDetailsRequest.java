package com.nexdin.nexdinstore.dto.request;

import lombok.Data;

@Data
public class ProductDetailsRequest {
    private String productID;
    private String size;
    private String color;
    private int soldQuantity;
    private int stockQuantity;
    private String status;
}
