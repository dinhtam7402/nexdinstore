package com.nexdin.store.service.impl;

import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.entity.ProductVariant;
import com.nexdin.store.mapper.ClientOrderDetailMapper;
import com.nexdin.store.payload.response.ClientOrderDetail;
import com.nexdin.store.repository.OrderDetailRepository;
import com.nexdin.store.service.OrderDetailService;
import com.nexdin.store.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductVariantService productVariantService;
    private final ClientOrderDetailMapper clientOrderDetailMapper;

    @Override
    public List<ClientOrderDetail> saveOrderDetail(Map<Integer, Integer> products, Orders orders) {
        List<ClientOrderDetail> orderDetails = new ArrayList<>();

        products.forEach((productVariantId, quantity) -> {
            ProductVariant productVariant = productVariantService.getAndLockById(productVariantId);
            if (productVariant.getQuantity() < quantity || quantity <= 0) throw new IllegalArgumentException("Product " + productVariant.getId() + " is not enough in stock");
            OrderDetail orderDetail = orderDetailRepository.save(new OrderDetail(orders, productVariant, quantity, productVariant.getProduct().getPrice()));
            orderDetails.add(clientOrderDetailMapper.entityToResponse(orderDetail));
            productVariantService.setQuantity(productVariant, quantity);
        });

        return orderDetails;
    }
}
