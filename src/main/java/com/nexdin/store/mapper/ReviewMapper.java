package com.nexdin.store.mapper;

import com.nexdin.store.entity.Review;
import com.nexdin.store.payload.request.ReviewRequest;
import com.nexdin.store.payload.response.ReviewResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface ReviewMapper extends GenericMapper<Review, ReviewRequest, ReviewResponse> {
    @Override
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "product", source = "productId")
    Review requestToEntity(ReviewRequest request);

    @Override
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "product", source = "productId")
    Review partialUpdate(@MappingTarget Review entity, ReviewRequest request);
}
