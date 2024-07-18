package com.personalization.service;

import com.personalization.client.datateam.DataTeamAPI;
import com.personalization.client.datateam.dto.DataTeamProductMetadata;
import com.personalization.mapper.DataTeamProductMetadataMapperImpl;
import com.personalization.repository.ShopperProductMetadataRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    private final DataTeamAPI dataTeamAPI;

    private final ShopperProductMetadataRepository shopperProductMetadataRepository;
    private final DataTeamProductMetadataMapperImpl productMetadataMapper;

    public ProductService(DataTeamAPI dataTeamAPI, ShopperProductMetadataRepository shopperProductMetadataRepository, DataTeamProductMetadataMapperImpl productMetadataMapper) {
        this.dataTeamAPI = dataTeamAPI;
        this.shopperProductMetadataRepository = shopperProductMetadataRepository;
        this.productMetadataMapper = productMetadataMapper;
    }

    public CompletableFuture<DataTeamProductMetadata> getDataTeamProductMetadata(String productId) {
        return shopperProductMetadataRepository.findByProductId(productId).thenCompose(existingMetadata -> {
            if (existingMetadata == null) {
                return CompletableFuture.supplyAsync(() -> dataTeamAPI.getDataTeamProductMetadata(productId));
            } else {
                return CompletableFuture.completedFuture(productMetadataMapper.toDataTeamProductMetadata(existingMetadata.getProductMetadata()));
            }
        });
    }

}
