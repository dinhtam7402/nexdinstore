package com.nexdin.nexdinstore.service.impl;

import com.nexdin.nexdinstore.domain.ProductDetails;
import com.nexdin.nexdinstore.domain.Products;
import com.nexdin.nexdinstore.dto.request.ProductDetailsRequest;
import com.nexdin.nexdinstore.repository.IProductDetailsRepository;
import com.nexdin.nexdinstore.service.IProductDetailsService;
import com.nexdin.nexdinstore.service.IProductService;
import com.nexdin.nexdinstore.utils.Factory;
import com.nexdin.nexdinstore.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProductDetailsService implements IProductDetailsService {
    @Autowired
    private IProductDetailsRepository productDetailsRepository;

    @Autowired
    private IProductService productService;


    @Override
    public ProductDetails getProductDetailsByID(String productDetailsID) {
        return productDetailsRepository.findById(productDetailsID).orElseThrow(() -> {
            log.warn("Not found product details with ID: {}", productDetailsID);
            return new IllegalArgumentException("Product details does not exist: " + productDetailsID);
        });
    }

    @Override
    public List<ProductDetails> getAllProductDetails() {
        List<ProductDetails> productDetails = productDetailsRepository.findAll();
        log.info("Retrieved {} product details", productDetails.size());
        return productDetails;
    }

    @Override
    public List<ProductDetails> getAllProductDetailsByProductID(String productID) {
        Products product = productService.getProductByID(productID);
        List<ProductDetails> productDetails = productDetailsRepository.findByProductProductID(productID);
        log.info("Retrieved {} product details {}", productDetails.size(), product.getProductName());
        return productDetails;
    }

    @Override
    public ProductDetails create(ProductDetailsRequest request) {
        Products product = productService.getProductByID(request.getProductID());

        ProductDetails productDetail = ProductDetails.builder()
                .productDetailsID(IDGenerator.generateID())
                .product(product)
                .size(Factory.getSizeInstance(request.getSize()))
                .color(request.getColor())
                .soldQuantity(request.getSoldQuantity())
                .stockQuantity(request.getStockQuantity())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(Factory.getProductStatusInstance(request.getStatus()))
                .build();
        productDetailsRepository.save(productDetail);
        log.info("Created {} successfully", productDetail.getProductDetailsID());
        return productDetail;
    }

    @Override
    public ProductDetails update(String productDetailsID, ProductDetailsRequest request) {
        ProductDetails productDetails = getProductDetailsByID(productDetailsID);

        productDetails.setSize(Factory.getSizeInstance(request.getSize()));
        productDetails.setColor(request.getColor());
        productDetails.setSoldQuantity(request.getSoldQuantity());
        productDetails.setStockQuantity(request.getStockQuantity());
        productDetails.setUpdatedAt(LocalDateTime.now());
        productDetails.setStatus(Factory.getProductStatusInstance(request.getStatus()));
        productDetailsRepository.save(productDetails);
        log.info("Updated {} successfully", productDetailsID);

        return productDetails;
    }

    @Override
    public void delete(String productDetailsID) {
        ProductDetails productDetails = getProductDetailsByID(productDetailsID);
        productDetailsRepository.deleteById(productDetailsID);
        log.info("Deleted {} successfully", productDetails);
    }
}
