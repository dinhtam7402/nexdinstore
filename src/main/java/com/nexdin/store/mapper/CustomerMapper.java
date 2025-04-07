package com.nexdin.store.mapper;

import com.nexdin.store.entity.Customer;
import com.nexdin.store.payload.request.CustomerRequest;
import com.nexdin.store.payload.response.CustomerResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface CustomerMapper extends GenericMapper<Customer, CustomerRequest, CustomerResponse> {
    @Override
    @Mapping(target = "user", source = "userId")
    Customer requestToEntity(CustomerRequest request);

    @Override
    @Mapping(target = "user", source = "userId")
    List<Customer> requestToEntity(List<CustomerRequest> requests);

    @Override
    @Mapping(target = "user", source = "userId")
    Customer partialUpdate(@MappingTarget Customer entity, CustomerRequest request);
}
