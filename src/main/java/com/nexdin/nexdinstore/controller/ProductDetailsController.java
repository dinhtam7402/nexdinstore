package com.nexdin.nexdinstore.controller;

import com.nexdin.nexdinstore.domain.ProductDetails;
import com.nexdin.nexdinstore.dto.request.ProductDetailsRequest;
import com.nexdin.nexdinstore.dto.response.Response;
import com.nexdin.nexdinstore.service.IProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductDetailsController {
    @Autowired
    private IProductDetailsService productDetailsService;

    @GetMapping("/product-details/get-all")
    public ResponseEntity<Response<?>> getAllProductDetails() {
        List<ProductDetails> productDetails = productDetailsService.getAllProductDetails();
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder().httpStatus(HttpStatus.OK)
                        .message("Get all successfully")
                        .result(productDetails)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/product-details/get-by-id/{productDetailsID}")
    public ResponseEntity<Response<?>> getProductDetailsByID(@PathVariable String productDetailsID) {
        ProductDetails productDetails = productDetailsService.getProductDetailsByID(productDetailsID);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder().httpStatus(HttpStatus.OK)
                        .message("Successfully")
                        .result(productDetails)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/product-details/get-all-by-productID/{productID}")
    public ResponseEntity<Response<?>> getAllByProductID(@PathVariable String productID) {
        List<ProductDetails> productDetails = productDetailsService.getAllProductDetailsByProductID(productID);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder().httpStatus(HttpStatus.OK)
                        .message("Successfully")
                        .result(productDetails)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/product-details/create")
    public ResponseEntity<Response<?>> createProductDetails(@RequestBody ProductDetailsRequest request) {
        ProductDetails productDetails = productDetailsService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder().httpStatus(HttpStatus.CREATED)
                        .message("Successfully")
                        .result(productDetails)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/product-details/update/{productDetailsID}")
    public ResponseEntity<Response<?>> updateProductDetails(@PathVariable String productDetailsID, @RequestBody ProductDetailsRequest request) {
        ProductDetails productDetails = productDetailsService.update(productDetailsID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder().httpStatus(HttpStatus.CREATED)
                        .message("Successfully")
                        .result(productDetails)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/product-details/delete/{productDetailsID}")
    public ResponseEntity<Response<?>> deleteProductDetails(@PathVariable String productDetailsID) {
        productDetailsService.delete(productDetailsID);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder().httpStatus(HttpStatus.CREATED)
                        .message("Successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
