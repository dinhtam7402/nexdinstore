package com.nexdin.store.mapper;

import com.nexdin.store.entity.Provider;
import com.nexdin.store.payload.request.ProviderRequest;
import com.nexdin.store.payload.response.ProviderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProviderMapper extends GenericMapper<Provider, ProviderRequest, ProviderResponse> {
}
