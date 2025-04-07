package com.nexdin.store.mapper;

import com.nexdin.store.entity.ProductVariant;
import com.nexdin.store.payload.request.ProductVariantRequest;
import com.nexdin.store.payload.response.ProductVariantResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface ProductVariantMapper extends GenericMapper<ProductVariant, ProductVariantRequest, ProductVariantResponse> {
    @Override
    @Mapping(target = "product", source = "productId")
    ProductVariant requestToEntity(ProductVariantRequest request);
}
