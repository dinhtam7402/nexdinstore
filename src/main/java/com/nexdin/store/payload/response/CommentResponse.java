package com.nexdin.store.payload.response;

import com.nexdin.store.entity.Review;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentResponse extends BaseResponse {
    private Review review;
    private String comment;
}
