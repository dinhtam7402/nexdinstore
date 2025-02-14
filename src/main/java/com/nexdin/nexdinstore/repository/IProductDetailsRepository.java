package com.nexdin.nexdinstore.repository;

import com.nexdin.nexdinstore.domain.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductDetailsRepository extends JpaRepository<ProductDetails, String> {
    List<ProductDetails> findByProductProductID(String productID);
}
