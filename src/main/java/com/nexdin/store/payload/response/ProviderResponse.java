package com.nexdin.store.payload.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProviderResponse extends BaseResponse {
    private String name;
    private String contact;
    private Boolean isActive;
}
