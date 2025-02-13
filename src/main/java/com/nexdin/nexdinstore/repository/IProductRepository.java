package com.nexdin.nexdinstore.repository;

import com.nexdin.nexdinstore.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Products, String> {
    List<Products> findByProductNameContainingIgnoreCase(String keyName);
    List<Products> findByCategoryCategoryID(String categoryID);
    List<Products> findBySellingPriceBetween(int minPrice, int maxPrice);
}
