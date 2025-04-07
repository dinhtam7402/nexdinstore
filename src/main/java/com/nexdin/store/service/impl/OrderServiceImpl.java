package com.nexdin.store.service.impl;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.entity.enums.OrderStatus;
import com.nexdin.store.mapper.ClientInfoMapper;
import com.nexdin.store.mapper.ClientOrderDetailMapper;
import com.nexdin.store.payload.request.ClientOrderRequest;
import com.nexdin.store.payload.response.ClientInfoResponse;
import com.nexdin.store.payload.response.ClientOrderDetail;
import com.nexdin.store.payload.response.ClientOrderResponse;
import com.nexdin.store.repository.OrderRepository;
import com.nexdin.store.service.CustomerService;
import com.nexdin.store.service.OrderDetailService;
import com.nexdin.store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ClientInfoMapper clientInfoMapper;
    private final ClientOrderDetailMapper clientOrderDetailMapper;

    @Override
    @Transactional
    public ClientOrderResponse placeOrder(ClientOrderRequest request) {
        // Kiểm tra số lượng sản phẩm có đủ không và Lưu thông tin chi tiết đặt hàng
        List<OrderDetail> orderDetails = orderDetailService.saveOrderDetail(request.getProduct());

        int totalPrice = orderDetails.stream().mapToInt(OrderDetail::getPrice).sum();

        // Lưu thông tin khách hàng
        Customer customer = customerService.saveCustomerOrder(request.getCustomer());

        Orders orders = new Orders();
        orders.setStatus(OrderStatus.PENDING);
        orders.setTotalPrice(totalPrice);
        orders.setCustomer(customer);
        orderRepository.save(orders);

        orderDetailService.linkAndSaveDetails(orderDetails, orders);

        ClientInfoResponse infoResponse = clientInfoMapper.entityToResponse(customer);
        return new ClientOrderResponse(orders.getId(), infoResponse, orders.getTotalPrice(), clientOrderDetailMapper.entityToResponse(orderDetails));
    }
}
