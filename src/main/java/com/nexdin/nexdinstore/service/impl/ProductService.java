package com.nexdin.nexdinstore.service.impl;

import com.nexdin.nexdinstore.domain.Categories;
import com.nexdin.nexdinstore.domain.Products;
import com.nexdin.nexdinstore.dto.request.ProductRequest;
import com.nexdin.nexdinstore.repository.IProductRepository;
import com.nexdin.nexdinstore.service.ICategoryService;
import com.nexdin.nexdinstore.service.IProductService;
import com.nexdin.nexdinstore.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public Products getProductByID(String productID) {
        return productRepository.findById(productID)
                .orElseThrow(() -> {
                    log.warn("Product '{}' not found", productID);
                    return new IllegalArgumentException("Product does not exist: " + productID);
                });
    }

    @Override
    public List<Products> getAllProducts() {
        List<Products> products = productRepository.findAll();
        log.info("Retrieved {} products", products.size());
        return products;
    }

    @Override
    public Products createProduct(ProductRequest request) {
        Categories category = categoryService.getByCategoryName(request.getCategoryName());

        Products product = Products.builder()
                .productID(IDGenerator.generateID())
                .category(category)
                .productName(request.getProductName())
                .description(request.getDescription())
                .material(request.getMaterial())
                .importPrice(request.getImportPrice())
                .sellingPrice(request.getSellingPrice())
                .imageUrl(request.getImageUrl())
                .build();
        productRepository.save(product);
        log.info("Product '{}' created successfully with ID: {}", product.getProductName(), product.getProductID());
        return product;
    }

    @Override
    public Products updateProduct(String productID, ProductRequest request) {
        Products product = getProductByID(productID);
        Categories category = categoryService.getByCategoryName(request.getCategoryName());

        product.setCategory(category);
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setMaterial(request.getMaterial());
        product.setImportPrice(request.getImportPrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setImageUrl(request.getImageUrl());

        productRepository.save(product);
        log.info("Product updated successfully with ID: {}", productID);

        return product;
    }

    @Override
    public void deleteByID(String productID) {
        Products product = getProductByID(productID);
        productRepository.deleteById(productID);
        log.info("Deleted product with ID '{}' successfully", productID);
    }

    @Override
    public List<Products> searchProductsByName(String keyName) {
        List<Products> products = productRepository.findByProductNameContainingIgnoreCase(keyName);
        log.info("{} products found", products.size());
        return products;
    }

    @Override
    public List<Products> filterProductsByCategory(String categoryID) {
        boolean exists = categoryService.existsByCategoryID(categoryID);

        if (!exists) {
            log.warn("filterProductsByCategory - CategoryID '{}' does not exist", categoryID);
            throw new IllegalArgumentException("Category does not exist: " + categoryID);
        }

        List<Products> products = productRepository.findByCategoryCategoryID(categoryID);
        log.info("filterProductsByCategory - Retrieved {} products for category: '{}'", products.size(), categoryID);
        return products;
    }

    @Override
    public List<Products> filterProductsByPriceRange(int minPrice, int maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            log.warn("filterProductsByPriceRange - Invalid price range: minPrice={}, maxPrice={}", minPrice, maxPrice);
            throw new IllegalArgumentException("Invalid price range");
        }

        List<Products> products = productRepository.findBySellingPriceBetween(minPrice, maxPrice);
        log.info("filterProductsByPriceRange - Retrieved {} products in price range {} - {}", products.size(), minPrice, maxPrice);

        return products;
    }
}
