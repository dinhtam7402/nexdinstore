package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
}
