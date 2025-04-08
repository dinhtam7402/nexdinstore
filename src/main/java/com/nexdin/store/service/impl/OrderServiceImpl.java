package com.nexdin.store.service.impl;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.entity.ProductVariant;
import com.nexdin.store.entity.enums.PaymentType;
import com.nexdin.store.payload.request.client.ClientOrderRequest;
import com.nexdin.store.repository.OrderRepository;
import com.nexdin.store.service.CustomerService;
import com.nexdin.store.service.OrderService;
import com.nexdin.store.service.ProductVariantService;
import com.nexdin.store.service.payment.PaymentStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductVariantService productVariantService;
    private final CustomerService customerService;
    private final PaymentStrategyFactory paymentStrategyFactory;

    @Override
    @Transactional
    public Orders placeOrder(ClientOrderRequest request) {
        Orders orders = new Orders();

        List<ProductVariant> productVariants = productVariantService.reserveProductVariants(request.getProduct());

        List<OrderDetail> orderDetails = buildOrderDetails(orders, productVariants, request.getProduct());

        Integer totalPrice = calculateTotalPrice(orderDetails);

        Customer customer = customerService.createGuestCustomer(request.getCustomer());

        PaymentType type = EnumUtils.findEnumInsensitiveCase(PaymentType.class, request.getType());

        orders.setCustomer(customer);
        orders.setType(type);
        orders.setTotalPrice(totalPrice);
        orders.setOrderDetails(orderDetails);

        paymentStrategyFactory.getStrategy(type).pay(orders);

        return orderRepository.save(orders);
    }

    private List<OrderDetail> buildOrderDetails(Orders orders, List<ProductVariant> productVariants, Map<Integer, Integer> quantityMap) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (ProductVariant productVariant : productVariants) {
            Integer quantity = quantityMap.get(productVariant.getId());
            Integer price = productVariant.getProduct().getPrice();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(orders);
            orderDetail.setProduct(productVariant);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(price);

            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }

    private Integer calculateTotalPrice(List<OrderDetail> orderDetails) {
        return orderDetails.stream().mapToInt(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity()).sum();
    }
}
