package com.nexdin.store.controller;

import com.nexdin.store.entity.Orders;
import com.nexdin.store.mapper.client.ClientOrderMapper;
import com.nexdin.store.payload.Success;
import com.nexdin.store.payload.request.client.ClientOrderRequest;
import com.nexdin.store.payload.response.client.ClientOrderResponse;
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
@RequestMapping("/client/api/order")
@RequiredArgsConstructor
public class ClientOrderController {
    private final OrderService orderService;
    private final ClientOrderMapper mapper;

    @PostMapping
    public ResponseEntity<Success<?>> placeOrder(@RequestBody ClientOrderRequest request) {
        Orders order = orderService.placeOrder(request);

        return ResponseEntity.status(HttpStatus.OK).body(new Success<>(
                200,
                "ordered",
                LocalDateTime.now(),
                mapper.entityToResponse(order)
        ));
    }
}
