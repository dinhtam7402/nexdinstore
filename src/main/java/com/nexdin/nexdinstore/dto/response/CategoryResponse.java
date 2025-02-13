package com.nexdin.nexdinstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private String categoryID;
    private String categoryName;
}
