package com.nexdin.store.service;

import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.payload.response.ClientOrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    List<OrderDetail> saveOrderDetail(Map<Integer, Integer> products);
    void linkAndSaveDetails(List<OrderDetail> orderDetails, Orders orders);
}
