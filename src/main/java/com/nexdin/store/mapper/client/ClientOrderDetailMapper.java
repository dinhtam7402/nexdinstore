package com.nexdin.store.mapper.client;

import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.mapper.GenericMapper;
import com.nexdin.store.payload.response.client.ClientOrderDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientOrderDetailMapper extends GenericMapper<OrderDetail, OrderDetail, ClientOrderDetailResponse> {
    @Override
    @Mapping(target = "product", source = "product.product.id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    ClientOrderDetailResponse entityToResponse(OrderDetail entity);

    @Override
    @Mapping(target = "product", source = "product.product.id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    List<ClientOrderDetailResponse> entityToResponse(List<OrderDetail> orderDetails);
}
