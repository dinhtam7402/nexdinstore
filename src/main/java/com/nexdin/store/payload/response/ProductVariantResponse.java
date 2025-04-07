package com.nexdin.store.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexdin.store.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductVariantResponse {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;
    private Product product;
    private String size;
    private String color;
    private Integer quantity;
}
