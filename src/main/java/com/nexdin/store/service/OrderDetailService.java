package com.nexdin.store.service;

import com.nexdin.store.entity.Orders;
import com.nexdin.store.payload.response.ClientOrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    List<ClientOrderDetail> saveOrderDetail(Map<Integer, Integer> products, Orders orders);
}
