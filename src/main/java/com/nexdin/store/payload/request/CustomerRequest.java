package com.nexdin.store.payload.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private String userId;
    private String name;
    private String phone;
    private String email;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String notes;
    private Boolean isDefault;
}
