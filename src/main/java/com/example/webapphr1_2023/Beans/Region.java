package com.example.webapphr1_2023.Beans;

import java.math.BigDecimal;

public class Region {
    private BigDecimal regionId;
    private String regionName;

    public BigDecimal getRegionId() {
        return regionId;
    }

    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
