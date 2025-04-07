package com.nexdin.store.mapper;

import com.nexdin.store.entity.Category;
import com.nexdin.store.payload.request.CategoryRequest;
import com.nexdin.store.payload.response.CategoryResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface CategoryMapper extends GenericMapper<Category, CategoryRequest, CategoryResponse> {
}
