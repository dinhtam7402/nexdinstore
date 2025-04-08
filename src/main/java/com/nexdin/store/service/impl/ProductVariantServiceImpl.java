package com.nexdin.store.service.impl;

import com.nexdin.store.entity.ProductVariant;
import com.nexdin.store.repository.ProductVariantRepository;
import com.nexdin.store.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;

    @Override
    public List<ProductVariant> reserveProductVariants(Map<Integer, Integer> products) {
        List<ProductVariant> productVariants = new ArrayList<>();

        products.forEach((productVariantId, quantity) -> {
            ProductVariant productVariant = productVariantRepository.findAndLockById(productVariantId);

            if (productVariant.getQuantity() < quantity || quantity <= 0) {
                throw new IllegalArgumentException("Not enough stock for product variant " + productVariantId);
            }

            productVariant.setQuantity(productVariant.getQuantity() - quantity);
            productVariants.add(productVariant);
        });

        return productVariants;
    }
}
