package com.nexdin.store.mapper;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.payload.request.ClientInfoRequest;
import com.nexdin.store.payload.response.ClientInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientInfoMapper extends GenericMapper<Customer, ClientInfoRequest, ClientInfoResponse> {
}
