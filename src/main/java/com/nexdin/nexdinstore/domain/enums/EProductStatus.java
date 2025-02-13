package com.nexdin.nexdinstore.domain.enums;

public enum EProductStatus {
    AVAILABLE,      // Còn hàng, có thể bán
    OUT_OF_STOCK,   // Hết hàng
    DISCONTINUED,   // Ngừng kinh doanh (không bán nữa)
    PREORDER,       // Đặt hàng trước (chưa có sẵn)
    BACKORDER,      // Đang nhập hàng (tạm thời hết nhưng sẽ có lại)
    DAMAGED,        // Hư hỏng, không bán được
    RESERVED        // Đã được đặt trước (chưa hoàn thành giao dịch)
}
