package com.nexdin.store.mapper;

import com.nexdin.store.entity.Comment;
import com.nexdin.store.payload.request.CommentRequest;
import com.nexdin.store.payload.response.CommentResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface CommentMapper extends GenericMapper<Comment, CommentRequest, CommentResponse> {
    @Override
    @Mapping(target = "review", source = "reviewId")
    Comment requestToEntity(CommentRequest request);

    @Override
    @Mapping(target = "review", source = "reviewId")
    List<Comment> requestToEntity(List<CommentRequest> requests);

    @Override
    @Mapping(target = "review", source = "reviewId")
    Comment partialUpdate(@MappingTarget Comment entity, CommentRequest request);
}
