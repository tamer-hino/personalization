package com.personalization.client.datateam;


import com.personalization.client.datateam.dto.DataTeamProduct;
import com.personalization.client.datateam.dto.DataTeamProductMetadata;
import com.personalization.client.datateam.dto.DataTeamShopper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class MockDataTeamAPI implements DataTeamAPI {
    private static final Random random = new Random();
    private static final String[] categories = {"Electronics", "Babies", "Clothing", "Food", "Books"};
    private static final String[] brands = {"BrandA", "BrandB", "BrandC", "BrandD", "BrandE"};


    @Override
    public DataTeamShopper getShopperPersonalizedProductList(String shopperId) {
        int productCount = random.nextInt(5) + 1;

        List<DataTeamProduct> shelf = new ArrayList<>();
        for (int i = 0; i < productCount; i++) {
            shelf.add(generateRandomProduct());
        }

        DataTeamShopper shopper = new DataTeamShopper();
        shopper.setShopperId(shopperId);
        shopper.setShelf(shelf);

        return shopper;
    }

    public static DataTeamProduct generateRandomProduct() {
        String productId = UUID.randomUUID().toString();
        double relevancyScore = random.nextDouble() * 100;

        DataTeamProduct product = new DataTeamProduct();
        product.setProductId(productId);
        product.setRelevancyScore(relevancyScore);

        return product;
    }

    @Override
    public DataTeamProductMetadata getDataTeamProductMetadata(String productId) {
        String category = categories[random.nextInt(categories.length)];
        String brand = brands[random.nextInt(brands.length)];

        DataTeamProductMetadata productMetadata = new DataTeamProductMetadata();
        productMetadata.setProductId(productId);
        productMetadata.setCategory(category);
        productMetadata.setBrand(brand);

        return productMetadata;
    }
}
