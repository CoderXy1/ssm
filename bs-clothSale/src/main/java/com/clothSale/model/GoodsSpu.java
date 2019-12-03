package com.clothSale.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsSpu {
    private String spuId;

    private String goodsName;

    private BigDecimal lowPrice;

    private String spuIconId;

    private String categoryId;

    private String brandId;

    private Integer spuOrder;

    private Date gmtCreate;

    private Date gmtUpdate;

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId == null ? null : spuId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getSpuIconId() {
        return spuIconId;
    }

    public void setSpuIconId(String spuIconId) {
        this.spuIconId = spuIconId == null ? null : spuIconId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public Integer getSpuOrder() {
        return spuOrder;
    }

    public void setSpuOrder(Integer spuOrder) {
        this.spuOrder = spuOrder;
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
}