package com.nexdin.store.service.impl;

import com.nexdin.store.constant.FieldName;
import com.nexdin.store.constant.ResourceName;
import com.nexdin.store.entity.ProductVariant;
import com.nexdin.store.exception.ResourceNotFoundException;
import com.nexdin.store.repository.ProductRepository;
import com.nexdin.store.repository.ProductVariantRepository;
import com.nexdin.store.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository repository;
    private final ProductRepository productRepository;

    @Override
    public ProductVariant getAndLockById(Integer productId) {
        ProductVariant productVariant = repository.findAndLockById(productId);
        if (productVariant == null) throw new ResourceNotFoundException(ResourceName.PRODUCT_VARIANT, FieldName.ID, productId);
        return productVariant;
    }

    @Override
    public Integer getPrice(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(ResourceName.PRODUCT, FieldName.ID, productId)).getPrice();
    }

    @Override
    public void setQuantity(ProductVariant productVariant, Integer quantity) {
        productVariant.setQuantity(productVariant.getQuantity() - quantity);
    }
}
