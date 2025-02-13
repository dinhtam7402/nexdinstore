package com.nexdin.nexdinstore.service;

import com.nexdin.nexdinstore.domain.Categories;
import com.nexdin.nexdinstore.dto.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Optional<Categories> getByCategoryName(String categoryName);
    List<Categories> getAllCategories();
    boolean existsByCategoryName(String categoryName);
    Categories createCategory(CategoryRequest request);
    Categories updateCategory(String categoryID, CategoryRequest request);
    boolean deleteCategoryByCategoryID(String categoryID);
    boolean deleteCategoryByCategoryName(String categoryName);
}
