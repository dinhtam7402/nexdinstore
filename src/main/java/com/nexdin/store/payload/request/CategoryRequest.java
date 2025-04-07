package com.nexdin.store.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
}
