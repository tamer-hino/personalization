package com.personalization.mapper;

import com.personalization.entity.ProductMetadata;
import com.personalization.client.datateam.dto.DataTeamProductMetadata;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DataTeamProductMetadataMapper {

    DataTeamProductMetadata toDataTeamProductMetadata(ProductMetadata productMetadata);
}