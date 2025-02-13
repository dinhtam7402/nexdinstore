package com.nexdin.nexdinstore.controller;

import com.nexdin.nexdinstore.domain.Products;
import com.nexdin.nexdinstore.dto.request.ProductRequest;
import com.nexdin.nexdinstore.dto.response.Response;
import com.nexdin.nexdinstore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/product/get-all")
    public ResponseEntity<Response<?>> getAllProduct() {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Get All Products Successfully")
                        .result(products)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/product/get-by-id/{productID}")
    public ResponseEntity<Response<?>> getProductByID(@PathVariable String productID) {
        Products product = productService.getProductByID(productID);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Get product successfully")
                        .result(product)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/product/search-by-name")
    public ResponseEntity<Response<?>> searchProductByName(@RequestParam String keyName) {
        List<Products> products = productService.searchProductsByName(keyName);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Search products by name successfully")
                        .result(products)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/product/filter-by-category/{categoryID}")
    public ResponseEntity<Response<?>> filterProductByCategory(@PathVariable String categoryID) {
        List<Products> products = productService.filterProductsByCategory(categoryID);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Filter products by category successfully")
                        .result(products)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/product/filter-by-price-range")
    public ResponseEntity<Response<?>> filterProductsByPriceRange(@RequestParam int minPrice, @RequestParam int maxPrice) {
        List<Products> products = productService.filterProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Filter products by price range successfully")
                        .result(products)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/product/create")
    public ResponseEntity<Response<?>> createProduct(@RequestBody ProductRequest request) {
        Products product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("Created product successfully")
                        .result(product)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/product/update/{productID}")
    public ResponseEntity<Response<?>> updateProduct(@PathVariable String productID, @RequestBody ProductRequest request) {
        Products product = productService.updateProduct(productID, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Updated product successfully")
                        .result(product)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/product/delete/{productID}")
    public ResponseEntity<Response<?>> deleteProduct(@PathVariable String productID) {
        productService.deleteByID(productID);
        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Deleted product successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
