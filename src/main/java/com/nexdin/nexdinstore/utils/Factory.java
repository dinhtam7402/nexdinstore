package com.nexdin.nexdinstore.utils;

import com.nexdin.nexdinstore.domain.enums.EProductStatus;
import com.nexdin.nexdinstore.domain.enums.ESize;
import org.springframework.stereotype.Component;

@Component
public class Factory {
    public static ESize getSizeInstance(String value) {
        try {
            return ESize.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid size: " + value, e);
        }
    }

    public static EProductStatus getProductStatusInstance(String value) {
        try {
            return EProductStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product status: " + value, e);
        }
    }
}
