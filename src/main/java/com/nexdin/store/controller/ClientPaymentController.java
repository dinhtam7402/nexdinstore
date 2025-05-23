package com.nexdin.store.controller;

import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.entity.enums.OrderStatus;
import com.nexdin.store.payload.Error;
import com.nexdin.store.payload.Success;
import com.nexdin.store.repository.OrderDetailRepository;
import com.nexdin.store.repository.OrderRepository;
import com.nexdin.store.service.payment.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/client/api/payment")
@RequiredArgsConstructor
public class ClientPaymentController {
    private final PaymentService paymentService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @GetMapping("/vn-pay/{orderId}")
    public ResponseEntity<Success<?>> pay(@PathVariable Integer orderId, HttpServletRequest request) {
        return ResponseEntity.ok(new Success<>(200, "ok", LocalDateTime.now(), paymentService.createVnPayPayment(orderId, request)));
    }

    @GetMapping("/success")
    public ResponseEntity<?> success(HttpServletRequest request) {
        String responseCode = request.getParameter("vnp_ResponseCode");
        Orders order = orderRepository.findById(Integer.parseInt(request.getParameter("vnp_TxnRef"))).orElseThrow();


        if (responseCode.equalsIgnoreCase("00")) {
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
            return ResponseEntity.ok(new Success<>(200, "Thanh toan thanh cong", LocalDateTime.now(), null));
        } else {
            order.setStatus(OrderStatus.CANCELLED);
            List<OrderDetail> orderDetails = order.getOrderDetails();
            orderDetails.forEach(orderDetail -> {
                orderDetail.getProduct().setQuantity(orderDetail.getProduct().getQuantity() + orderDetail.getQuantity());
                orderDetailRepository.save(orderDetail);
            });
            return ResponseEntity.badRequest().body(new Error<>(400, LocalDateTime.now(), "Thanh toan khong thanh cong", null));
        }
    }
}
