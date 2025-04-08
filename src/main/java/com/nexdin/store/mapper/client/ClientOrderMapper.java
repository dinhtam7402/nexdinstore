package com.nexdin.store.mapper.client;

import com.nexdin.store.entity.Orders;
import com.nexdin.store.mapper.GenericMapper;
import com.nexdin.store.payload.request.client.ClientOrderRequest;
import com.nexdin.store.payload.response.client.ClientOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {ClientInfoMapper.class, ClientOrderDetailMapper.class})
public interface ClientOrderMapper extends GenericMapper<Orders, ClientOrderRequest, ClientOrderResponse> {
}
