package com.nexdin.nexdinstore.domain.enums;

public enum EOrderStatus {
    PENDING,        // 🕒 Đang chờ xử lý (mới tạo, chưa duyệt)
    CONFIRMED,      // ✅ Đã xác nhận (cửa hàng đã duyệt)
    PROCESSING,     // ⚙️ Đang xử lý (đang chuẩn bị hàng)
    SHIPPED,        // 🚚 Đang giao hàng
    DELIVERED,      // 📦 Đã giao hàng thành công
    CANCELED,       // ❌ Đã hủy (khách hủy hoặc hệ thống từ chối)
    RETURNED,       // 🔄 Đã trả hàng
    FAILED          // ⚠️ Giao hàng thất bại (khách không nhận, lỗi vận chuyển)
}
