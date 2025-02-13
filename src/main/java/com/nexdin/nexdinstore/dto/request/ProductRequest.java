package com.nexdin.nexdinstore.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String categoryName;
    private String productName;
    private String description;
    private String material;
    private int importPrice;
    private int sellingPrice;
    private String imageUrl;
}
