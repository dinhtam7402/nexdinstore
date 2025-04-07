package com.nexdin.store.util;

import com.nexdin.store.constant.FieldName;
import com.nexdin.store.constant.ResourceName;
import com.nexdin.store.entity.*;
import com.nexdin.store.entity.enums.PaymentType;
import com.nexdin.store.exception.ResourceNotFoundException;
import com.nexdin.store.repository.*;
import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.util.EnumUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class MapperUtils {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProviderRepository providerRepository;

    public Category mapToCategory(@Nullable Integer categoryId) {return categoryId == null ? null : categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(ResourceName.CATEGORY, FieldName.ID, categoryId));}

    public Product mapToProduct(@Nullable Integer productId) {return productId == null ? null : productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(ResourceName.PRODUCT, FieldName.ID, productId));}

    public User mapToUser(@Nullable Integer userId) {return userId == null ? null : userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID, userId));}

    public Review mapToReview(@Nullable Integer reviewId) {return reviewId == null ? null : reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException(ResourceName.REVIEW, FieldName.ID, reviewId));}

    public Provider mapToProvider(@Nullable Integer providerId) {return providerId == null ? null : providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException(ResourceName.PROVIDER, FieldName.ID, providerId));}

    public PaymentType mapStringToPaymentType(String type) {return EnumUtils.findEnumInsensitiveCase(PaymentType.class, type);}

    public LocalDate mapStringToLocalDate(String expiry) {
        String[] parts = expiry.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        return LocalDate.of(year, month, 1);
    }
}
