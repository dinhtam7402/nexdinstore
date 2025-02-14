package com.nexdin.nexdinstore.service;

import com.nexdin.nexdinstore.domain.ProductDetails;
import com.nexdin.nexdinstore.dto.request.ProductDetailsRequest;

import java.util.List;

public interface IProductDetailsService {
    ProductDetails getProductDetailsByID(String productDetailsID);
    List<ProductDetails> getAllProductDetails();
    List<ProductDetails> getAllProductDetailsByProductID(String productID);
    ProductDetails create(ProductDetailsRequest request);
    ProductDetails update(String productDetailsID, ProductDetailsRequest request);
    void delete(String productDetailsID);
}
