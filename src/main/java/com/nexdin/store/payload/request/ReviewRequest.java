package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Integer userId;
    private Integer productId;
    private Integer rating;
}
