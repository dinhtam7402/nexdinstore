package com.nexdin.store.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Configuration
public class VNPayConfig {
    @Getter
    @Value("${vnp_PayUrl}")
    private String vnp_PayUrl;
    @Value("${vnp_ReturnUrl}")
    private String vnp_ReturnUrl;
    @Value("${vnp_TmnCode}")
    private String vnp_TmnCode;
    @Getter
    @Value("${secretKey}")
    private String secretKey;
    @Value("${vnp_Version}")
    private String vnp_Version;
    @Value("${vnp_Command}")
    private String vnp_Command;
    @Value("${vnp_OrderType}")
    private String vnp_OrderType;
    @Value("${vnp_ApiUrl}")
    private String vnp_ApiUrl;

    public Map<String, String> getVNPayConfig(String vnp_TxnRef) {
        Map<String, String> vnpParamsMap = new HashMap<>();
        // set transaction info
        vnpParamsMap.put("vnp_Version", this.vnp_Version);
        vnpParamsMap.put("vnp_Command", this.vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef", vnp_TxnRef);
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnpParamsMap.put("vnp_OrderType", this.vnp_OrderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);

        // set transaction time
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = format.format(calendar.getTime());
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = format.format(calendar.getTime());
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnpParamsMap;
    }
}
