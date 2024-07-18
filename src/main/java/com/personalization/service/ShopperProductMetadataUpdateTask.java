package com.personalization.service;

import com.personalization.client.datateam.DataTeamAPI;
import com.personalization.client.datateam.dto.DataTeamShopper;
import com.personalization.mapper.ShopperProductMetadataMapper;
import com.personalization.entity.Shopper;
import com.personalization.entity.ShopperProductMetadata;

import com.personalization.repository.ShopperProductMetadataRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ShopperProductMetadataUpdateTask {

    private final DataTeamAPI dataTeamAPI;

    private final ProductService productService;

    private final ShopperProductMetadataMapper metadataMapper;

    private final ShopperProductMetadataRepository shopperProductMetadataRepository;

    private final ShopperService shopperService;

    public ShopperProductMetadataUpdateTask(DataTeamAPI dataTeamAPI, ProductService productService, ShopperProductMetadataMapper metadataMapper, ShopperProductMetadataRepository shopperProductMetadataRepository, ShopperService shopperService) {
        this.dataTeamAPI = dataTeamAPI;
        this.productService = productService;
        this.metadataMapper = metadataMapper;
        this.shopperProductMetadataRepository = shopperProductMetadataRepository;
        this.shopperService = shopperService;
    }

    public void updateShopperProductMetadata(Shopper shopper) {
        CompletableFuture.supplyAsync(() -> dataTeamAPI.getShopperPersonalizedProductList(shopper.getShopperId()))
                .thenAcceptAsync(shopperPersonalizedProductList -> {
                    processShopperPersonalizedProductList(shopper, shopperPersonalizedProductList);
                }).join();
    }

    private void processShopperPersonalizedProductList(Shopper shopper, DataTeamShopper shopperPersonalizedProductList) {
        List<CompletableFuture<Void>> metadataSaveFutures = shopperPersonalizedProductList.getShelf().stream()
                .map(dataTeamProduct -> productService.getDataTeamProductMetadata(dataTeamProduct.getProductId())
                        .thenAcceptAsync(dataTeamProductMetadata -> {
                            ShopperProductMetadata shopperProductMetadata = metadataMapper.toShopperProductMetadata(shopperPersonalizedProductList, dataTeamProductMetadata, dataTeamProduct);
                            shopperProductMetadataRepository.save(shopperProductMetadata);
                        })
                ).toList();

        CompletableFuture.allOf(metadataSaveFutures.toArray(new CompletableFuture[0])).join();
        shopperService.updateShopperLastUpdateTime(shopper);
    }

}
