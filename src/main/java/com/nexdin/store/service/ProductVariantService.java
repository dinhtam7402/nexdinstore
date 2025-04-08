package com.nexdin.store.service;

import com.nexdin.store.entity.ProductVariant;

import java.util.List;
import java.util.Map;

public interface ProductVariantService {
    List<ProductVariant> reserveProductVariants(Map<Integer, Integer> products);
}
