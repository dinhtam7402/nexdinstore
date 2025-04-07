package com.nexdin.store.payload.response;

import com.nexdin.store.entity.Product;
import com.nexdin.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReviewResponse extends BaseResponse {
    private User user;
    private Product product;
    private Integer rating;
}
