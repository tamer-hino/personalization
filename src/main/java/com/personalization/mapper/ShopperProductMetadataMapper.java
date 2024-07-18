package com.personalization.mapper;

import com.personalization.client.datateam.dto.DataTeamProduct;
import com.personalization.client.datateam.dto.DataTeamProductMetadata;
import com.personalization.client.datateam.dto.DataTeamShopper;
import com.personalization.entity.ShopperProductMetadata;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShopperProductMetadataMapper {


    @Mappings({
            @Mapping(target = "relevancyScore", source = "dataTeamProduct.relevancyScore"),
            @Mapping(target = "shopper", source = "dataTeamShopper"),
            @Mapping(target = "productMetadata", source = "dataTeamProductMetadata")
    })
    ShopperProductMetadata toShopperProductMetadata(DataTeamShopper dataTeamShopper, DataTeamProductMetadata dataTeamProductMetadata, DataTeamProduct dataTeamProduct);

}
