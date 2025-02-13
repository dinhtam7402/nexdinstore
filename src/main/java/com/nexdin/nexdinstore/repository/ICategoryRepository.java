package com.nexdin.nexdinstore.repository;

import com.nexdin.nexdinstore.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Categories, String> {
    Optional<Categories> findByCategoryName(String categoryName);
    boolean existsByCategoryName(String categoryName);
}
