package com.nexdin.nexdinstore.service;

import com.nexdin.nexdinstore.domain.Products;
import com.nexdin.nexdinstore.dto.request.ProductRequest;

import java.util.List;

public interface IProductService {
    Products getProductByID(String productID);
    List<Products> getAllProducts();
    Products createProduct(ProductRequest request);
    Products updateProduct(String productID, ProductRequest request);
    void deleteByID(String productID);
    List<Products> searchProductsByName(String keyName);
    List<Products> filterProductsByCategory(String categoryID);
    List<Products> filterProductsByPriceRange(int minPrice, int maxPrice);
}
