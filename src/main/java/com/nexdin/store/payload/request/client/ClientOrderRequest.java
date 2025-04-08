package com.nexdin.store.payload.request.client;

import lombok.Data;

import java.util.Map;

@Data
public class ClientOrderRequest {
    /*
    * Map lưu thông tin sản phẩm trong đơn hàng
    * Key: ID của sản phẩm
    * Value: Số lượng sản phẩm
    * */
    private Map<Integer, Integer> product;
    private ClientInfoRequest customer;
    private String type;
}
