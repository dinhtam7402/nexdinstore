package com.nexdin.store.mapper;

import com.nexdin.store.entity.PaymentMethod;
import com.nexdin.store.payload.request.PaymentMethodRequest;
import com.nexdin.store.payload.response.PaymentMethodResponse;
import com.nexdin.store.util.MapperUtils;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = MapperUtils.class)
public interface PaymentMethodMapper extends GenericMapper<PaymentMethod, PaymentMethodRequest, PaymentMethodResponse> {
    @Override
    @Mapping(target = "provider", source = "providerId")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "expiry", source = "expiry")
    PaymentMethod requestToEntity(PaymentMethodRequest request);

    @Override
    @Mapping(target = "provider", source = "providerId")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "expiry", source = "expiry")
    List<PaymentMethod> requestToEntity(List<PaymentMethodRequest> requests);

    @Override
    @Mapping(target = "provider", source = "providerId")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "expiry", source = "expiry")
    PaymentMethod partialUpdate(@MappingTarget PaymentMethod entity, PaymentMethodRequest request);
}
