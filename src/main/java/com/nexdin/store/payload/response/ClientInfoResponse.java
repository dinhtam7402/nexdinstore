package com.nexdin.store.payload.response;

import lombok.Data;

@Data
public class ClientInfoResponse {
    private String name;
    private String phone;
    private String email;
    private String street;
    private String ward;
    private String district;
    private String province;
    private String notes;

}
