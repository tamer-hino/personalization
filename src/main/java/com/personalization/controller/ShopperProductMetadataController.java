package com.personalization.controller;

import com.personalization.payload.ShopperProductDto;
import com.personalization.service.ShopperProductMetadataService;
import com.personalization.service.ShopperProductMetadataServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopper-product-metadata")
public class ShopperProductMetadataController {

    private final ShopperProductMetadataService shopperProductMetadataServiceImp;

    public ShopperProductMetadataController(ShopperProductMetadataServiceImp shopperProductMetadataServiceImp) {
        this.shopperProductMetadataServiceImp = shopperProductMetadataServiceImp;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ShopperProductDto>> getFilteredShopperProductMetadata(
            @RequestParam(required = true) String shopperId,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<ShopperProductDto> metadataList = shopperProductMetadataServiceImp.getFilteredShopperProductMetadata(shopperId, brand, category, limit);
        return ResponseEntity.ok(metadataList);
    }
}

