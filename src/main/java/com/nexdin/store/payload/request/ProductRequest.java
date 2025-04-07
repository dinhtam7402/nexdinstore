package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Integer price;
    private String material;
    private String careInstructions;
    private Integer categoryId;
}
