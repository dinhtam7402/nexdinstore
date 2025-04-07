package com.nexdin.store.mapper;

import com.nexdin.store.entity.OrderDetail;
import com.nexdin.store.payload.response.ClientOrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientOrderDetailMapper extends GenericMapper<OrderDetail, Map<Integer, Integer>, ClientOrderDetail> {
    @Override
    @Mapping(target = "product", source = "product.product.id")
    ClientOrderDetail entityToResponse(OrderDetail entity);

    @Override
    @Mapping(target = "product", source = "product.product.id")
    List<ClientOrderDetail> entityToResponse(List<OrderDetail> entities);
}
