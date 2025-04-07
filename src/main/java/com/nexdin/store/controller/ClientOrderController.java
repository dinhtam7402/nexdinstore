package com.nexdin.store.controller;

import com.nexdin.store.entity.Orders;
import com.nexdin.store.payload.Success;
import com.nexdin.store.payload.request.ClientOrderRequest;
import com.nexdin.store.payload.response.ClientOrderResponse;
import com.nexdin.store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/client/api/place-order")
@RequiredArgsConstructor
public class ClientOrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Success<ClientOrderResponse>> placeOrderController(@RequestBody ClientOrderRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new Success<>(
                200,
                "ordered",
                LocalDateTime.now(),
                orderService.placeOrder(request)
        ));
    }
}
