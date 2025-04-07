package com.nexdin.store.service;

import com.nexdin.store.entity.Product;
import com.nexdin.store.entity.ProductVariant;

public interface ProductVariantService {
    void setQuantity(ProductVariant productVariant, Integer quantity);
    ProductVariant getAndLockById(Integer productId);
    Integer getPrice(Integer productId);
}
