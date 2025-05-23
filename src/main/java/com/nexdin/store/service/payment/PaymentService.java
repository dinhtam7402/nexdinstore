package com.nexdin.store.service.payment;

import com.nexdin.store.config.VNPayConfig;
import com.nexdin.store.entity.Orders;
import com.nexdin.store.payload.Success;
import com.nexdin.store.repository.OrderRepository;
import com.nexdin.store.util.VNPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final VNPayConfig vnPayConfig;
    private final OrderRepository orderRepository;

    public Success<?> createVnPayPayment(Integer orderId, HttpServletRequest request) {
        long amount = orderRepository.findById(orderId).orElseThrow().getTotalPrice() * 100L;
        String bankCode = request.getParameter("bankCode");
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(orderId.toString());
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtils.getIpAddress(request));

        String queryUrl = VNPayUtils.getPaymentUrl(vnpParamsMap, true);
        String hashData = VNPayUtils.getPaymentUrl(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtils.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;

        return new Success<>(200, "ok", LocalDateTime.now(), paymentUrl);
    }
}
