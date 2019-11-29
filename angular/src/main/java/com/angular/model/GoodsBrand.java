package com.angular.model;

import java.util.Date;

public class GoodsBrand {
    private String brandId;

    private String brandName;

    private Integer brandOrder;

    private String brandIconId;

    private Date gmtCreate;

    private Date gmtUpdate;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Integer getBrandOrder() {
        return brandOrder;
    }

    public void setBrandOrder(Integer brandOrder) {
        this.brandOrder = brandOrder;
    }

    public String getBrandIconId() {
        return brandIconId;
    }

    public void setBrandIconId(String brandIconId) {
        this.brandIconId = brandIconId == null ? null : brandIconId.trim();
    }
}