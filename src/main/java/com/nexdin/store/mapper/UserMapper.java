package com.nexdin.store.mapper;

import com.nexdin.store.entity.User;
import com.nexdin.store.payload.request.UserRequest;
import com.nexdin.store.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends GenericMapper<User, UserRequest, UserResponse> {
}
