package com.nexdin.store.payload.response;

import com.nexdin.store.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerResponse extends BaseResponse {
    private User user;
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
