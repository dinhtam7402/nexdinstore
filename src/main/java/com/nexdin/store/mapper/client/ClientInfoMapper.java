package com.nexdin.store.mapper.client;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.mapper.GenericMapper;
import com.nexdin.store.payload.request.client.ClientInfoRequest;
import com.nexdin.store.payload.response.client.ClientInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientInfoMapper extends GenericMapper<Customer, ClientInfoRequest, ClientInfoResponse> {
}
