package com.personalization.client.datateam;


import com.personalization.client.datateam.dto.DataTeamProductMetadata;
import com.personalization.client.datateam.dto.DataTeamShopper;

public interface DataTeamAPI {
    DataTeamShopper getShopperPersonalizedProductList(String shopperId);
    DataTeamProductMetadata getDataTeamProductMetadata(String productIdId);
}
