package com.nexdin.nexdinstore.domain.enums;

public enum EPaymentStatus {
    PENDING,      // ⏳ Đang chờ thanh toán
    COMPLETED,    // ✅ Thanh toán thành công
    FAILED,       // ❌ Thanh toán thất bại
    CANCELED,     // 🚫 Thanh toán bị hủy bởi người dùng
    REFUNDED,     // 🔄 Đã hoàn tiền cho khách
    PROCESSING    // ⚙️ Đang xử lý thanh toán
}
