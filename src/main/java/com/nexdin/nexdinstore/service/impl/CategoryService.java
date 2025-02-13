package com.nexdin.nexdinstore.service.impl;

import com.nexdin.nexdinstore.domain.Categories;
import com.nexdin.nexdinstore.dto.request.CategoryRequest;
import com.nexdin.nexdinstore.repository.ICategoryRepository;
import com.nexdin.nexdinstore.service.ICategoryService;
import com.nexdin.nexdinstore.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Optional<Categories> getByCategoryName(String categoryName) {
        Optional<Categories> category = categoryRepository.findByCategoryName(categoryName);
        if (category.isPresent()) {
            log.info("Category found: {}", category);
        } else {
            log.warn("Category not found for name: {}", categoryName);
        }

        return category;
    }

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = categoryRepository.findAll();
        log.info("Total categories fetched: {}", categories.size());
        return categories;
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }

    @Override
    public Categories createCategory(CategoryRequest request) {
        boolean existsCategory = existsByCategoryName(request.getCategoryName());
        if (!existsCategory) {
            String newCategoryID = IDGenerator.generateID();
            log.info("Category '{}' does not exist. Creating new category with ID: {}", request.getCategoryName(), newCategoryID);

            Categories newCategory = Categories.builder()
                    .categoryID(newCategoryID)
                    .categoryName(request.getCategoryName())
                    .build();

            categoryRepository.save(newCategory);
            log.info("Category '{}' created successfully with ID: {}", request.getCategoryName(), newCategoryID);
            return newCategory;
        } else {
            log.warn("Category '{}' already exists. Skipping creation.", request.getCategoryName());
        }
        return null;
    }

    @Override
    public Categories updateCategory(String categoryID,  CategoryRequest request) {
        Optional<Categories> optionalCategory = categoryRepository.findById(categoryID);

        if (optionalCategory.isPresent()) {
            Categories category = optionalCategory.get();
            log.info("Category found: {} - Updating name to '{}'", category, request.getCategoryName());

            category.setCategoryName(request.getCategoryName());
            categoryRepository.save(category);

            log.info("Category with ID: {} successfully updated to '{}'", categoryID, request.getCategoryName());
            return category;
        } else {
            log.warn("Category with ID: {} not found. Update failed.", categoryID);
            return null;
        }
    }

    @Override
    public boolean deleteCategoryByCategoryID(String categoryID) {
        Optional<Categories> optionalCategory = categoryRepository.findById(categoryID);

        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(categoryID);
            log.info("Category with ID: {} successfully deleted.", categoryID);
            return true;
        } else {
            log.warn("Category with ID: {} not found. Deletion failed.", categoryID);
            return false;
        }
    }

    @Override
    public boolean deleteCategoryByCategoryName(String categoryName) {
        Optional<Categories> optionalCategory = categoryRepository.findByCategoryName(categoryName);
        if (optionalCategory.isPresent()) {
            String categoryID = optionalCategory.get().getCategoryID();
            categoryRepository.deleteById(categoryID);
            log.info("Category '{}' with ID: {} successfully deleted.", categoryName, categoryID);
            return true;
        } else {
            log.warn("Category '{}' not found. Deletion failed.", categoryName);
            return false;
        }
    }
}
