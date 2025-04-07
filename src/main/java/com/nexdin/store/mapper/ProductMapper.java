package com.nexdin.store.mapper;

import com.nexdin.store.entity.Product;
import com.nexdin.store.payload.request.ProductRequest;
import com.nexdin.store.payload.response.ProductResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface ProductMapper extends GenericMapper<Product, ProductRequest, ProductResponse> {
    @Override
    @Mapping(target = "category", source = "categoryId")
    Product requestToEntity(ProductRequest request);
}
