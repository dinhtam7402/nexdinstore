package com.nexdin.nexdinstore.service;

import com.nexdin.nexdinstore.domain.Categories;
import com.nexdin.nexdinstore.dto.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Categories getByCategoryName(String categoryName);
    List<Categories> getAllCategories();
    boolean existsByCategoryID(String categoryID);
    boolean existsByCategoryName(String categoryName);
    Categories createCategory(CategoryRequest request);
    Categories updateCategory(String categoryID, CategoryRequest request);
    void deleteCategoryByCategoryID(String categoryID);
    void deleteCategoryByCategoryName(String categoryName);
}
