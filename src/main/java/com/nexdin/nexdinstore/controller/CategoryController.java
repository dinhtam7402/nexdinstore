package com.nexdin.nexdinstore.controller;

import com.nexdin.nexdinstore.domain.Categories;
import com.nexdin.nexdinstore.dto.request.CategoryRequest;
import com.nexdin.nexdinstore.dto.response.Response;
import com.nexdin.nexdinstore.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/category/create")
    public ResponseEntity<Response<?>> createCategory(@RequestBody CategoryRequest request) {
        Categories categories = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                    Response.builder()
                            .httpStatus(HttpStatus.CREATED)
                            .message("Created Category Successfully")
                            .result(categories)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/category/update/{categoryID}")
    public ResponseEntity<Response<?>> updateCategory(@PathVariable String categoryID, @RequestBody CategoryRequest request) {
        Categories updatedCategory = categoryService.updateCategory(categoryID, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                    Response.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("ok")
                            .result(updatedCategory)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/category/delete-by-id/{categoryID}")
    public ResponseEntity<Response<?>> deleteByID(@PathVariable String categoryID) {
        categoryService.deleteCategoryByCategoryID(categoryID);
        return ResponseEntity.status(HttpStatus.OK).body(
                    Response.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("ok")
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/category/delete-by-name/{categoryName}")
    public ResponseEntity<Response<?>> deleteByName(@PathVariable String categoryName) {
        categoryService.deleteCategoryByCategoryName(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(
                    Response.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("ok")
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @GetMapping("/category/get-by-name/{categoryName}")
    public ResponseEntity<Response<?>> getByName(@PathVariable String categoryName) {
        Categories category = categoryService.getByCategoryName(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(
                    Response.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("ok")
                            .result(category)
                            .timestamp(LocalDateTime.now())
                            .build()
            );

    }

    @GetMapping("/category/get-all-categories")
    public ResponseEntity<Response<?>> getAllCategories() {
        List<Categories> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(
                    Response.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("ok")
                            .result(categories)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }
}
