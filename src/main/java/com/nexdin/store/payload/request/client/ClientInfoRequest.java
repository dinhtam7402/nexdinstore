package com.nexdin.store.payload.request.client;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientInfoRequest {
    @NotBlank(message = "Vui lòng nhập tên")
    private String name;
    @NotBlank(message = "Vui long nhập số điện thoại")
    private String phone;
    @NotBlank(message = "Vui lòng nhập địa chỉ email")
    private String email;
    @NotBlank(message = "Vui lòng nhập địa chỉ chi tiết nhập hàng")
    private String street;
    @NotBlank(message = "Vui lòng nhập phường/xã")
    private String ward;
    @NotBlank(message = "Vui lòng nhập quận/huyện")
    private String district;
    @NotBlank(message = "Vui lòng nhập tỉnh/thành phố")
    private String province;
    private String notes;
}
