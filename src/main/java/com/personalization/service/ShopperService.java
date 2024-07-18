package com.personalization.service;

import com.personalization.entity.Shopper;
import com.personalization.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShopperService {

    private final ShopperRepository shopperRepository;

    public ShopperService(ShopperRepository shopperRepository) {
        this.shopperRepository = shopperRepository;
    }

    public Shopper getOrCreateShopper(String shopperId) {
        Shopper shopper = shopperRepository.findByShopperId(shopperId);
        if (shopper == null) {
            shopper = new Shopper();
            shopper.setShopperId(shopperId);
            shopperRepository.save(shopper);
        }
        return shopper;
    }

    public void updateShopperLastUpdateTime(Shopper shopper) {
        shopper.setLastUpdated(new Date());
        shopperRepository.save(shopper);
    }
}
