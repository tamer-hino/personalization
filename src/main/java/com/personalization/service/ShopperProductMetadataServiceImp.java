package com.personalization.service;

import com.personalization.entity.Shopper;
import com.personalization.entity.ShopperProductMetadata;
import com.personalization.mapper.ShopperProductDtoMapper;
import com.personalization.payload.ShopperProductDto;
import com.personalization.specification.ShopperProductMetadataSpecifications;
import com.personalization.repository.ShopperProductMetadataRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

@Service
public class ShopperProductMetadataServiceImp implements ShopperProductMetadataService{

    @Value("${app.min-last-update}")
    private Duration minLastUpdate;


    private final ShopperProductMetadataRepository shopperProductMetadataRepository;
    private final ShopperService shopperService;
    private final ShopperProductMetadataUpdateTask shopperProductMetadataUpdateTask;
    private final ShopperProductDtoMapper shopperProductDtoMapper;

    public ShopperProductMetadataServiceImp(ShopperProductMetadataRepository shopperProductMetadataRepository, ShopperService shopperService, ShopperProductMetadataUpdateTask shopperProductMetadataUpdateTask, ShopperProductDtoMapper shopperProductDtoMapper) {
        this.shopperProductMetadataRepository = shopperProductMetadataRepository;
        this.shopperService = shopperService;
        this.shopperProductMetadataUpdateTask = shopperProductMetadataUpdateTask;
        this.shopperProductDtoMapper = shopperProductDtoMapper;
    }





    public List<ShopperProductDto> getFilteredShopperProductMetadata(String shopperId, String brand, String category, Integer limit) {
        Shopper shopper = shopperService.getOrCreateShopper(shopperId);

        if (shouldUpdateData(shopper)) {
            shopperProductMetadataUpdateTask.updateShopperProductMetadata(shopper);
        }
        Pageable pageable = PageRequest.of(0, limit);

        List<ShopperProductMetadata> content = shopperProductMetadataRepository.findAll(ShopperProductMetadataSpecifications.buildSpecification(shopperId, brand, category), pageable).getContent();

        return content.stream().map(c-> shopperProductDtoMapper.toProduct(c.getProductMetadata())).toList();
    }


    private boolean shouldUpdateData(Shopper shopper) {
        Date currentDate = new Date();
        Date minLastUpdate = new Date(currentDate.getTime() - this.minLastUpdate.getSeconds() * 60);
        return shopper.getLastUpdated() == null || shopper.getLastUpdated().before(minLastUpdate);
    }

}

