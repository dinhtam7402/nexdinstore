package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class CommentRequest {
    private Integer reviewId;
    private String comment;
}
