package com.nexdin.store.entity.enums;

public enum ShippingStatus {
    PENDING,            // Đơn hàng đã được tạo, nhưng chưa gửi yêu cầu tạo vận đơn đến đơn vị vận chuyển
    SHIPPED_REQUESTED,  // Yêu cầu gửi hàng đã được chuyển đến đơn vị vận chuyển
    DELIVERING,         // Đang giao
    DELIVERED,          // Đã giao
    CANCELED,           // Hủy bỏ
    RETURNED            // Đã hoàn
}
