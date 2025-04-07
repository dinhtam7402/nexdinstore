package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class ProviderRequest {
    private String name;
    private String contact;
    private Boolean isActive;
}
