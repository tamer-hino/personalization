package com.personalization.client.datateam.dto;

import lombok.Data;
import java.util.List;

@Data
public class DataTeamShopper {
    private String shopperId;
    private List<DataTeamProduct> shelf;
}

