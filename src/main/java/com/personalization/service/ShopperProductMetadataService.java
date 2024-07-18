package com.personalization.service;

import com.personalization.payload.ShopperProductDto;

import java.util.List;

public interface ShopperProductMetadataService {
    List<ShopperProductDto> getFilteredShopperProductMetadata(String shopperId, String brand, String category, Integer limit);

}
