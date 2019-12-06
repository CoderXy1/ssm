package com.clothSale.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsSku {
    private String skuId;

    private BigDecimal priceInput;

    private BigDecimal priceSale;

    private Integer stock;

    private String spuId;

    private Date gmtCreate;

    private Date gmtUpdate;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public BigDecimal getPriceInput() {
        return priceInput;
    }

    public void setPriceInput(BigDecimal priceInput) {
        this.priceInput = priceInput;
    }

    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId == null ? null : spuId.trim();
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