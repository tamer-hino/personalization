package com.personalization.mapper;

import com.personalization.entity.ProductMetadata;
import com.personalization.payload.ShopperProductDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,implementationName = "ShopperProductDtoMapperImpl")
public interface ShopperProductDtoMapper {

    ShopperProductDto toProduct(ProductMetadata productMetadata);
}