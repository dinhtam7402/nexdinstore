package com.nexdin.store.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexdin.store.entity.Provider;
import com.nexdin.store.entity.User;
import com.nexdin.store.entity.enums.PaymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentMethodResponse extends BaseResponse{
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private PaymentType type;
    private Provider provider;
    private User user;
    private String account;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM")
    private LocalDate expiry;
    private Boolean isDefault;
}
