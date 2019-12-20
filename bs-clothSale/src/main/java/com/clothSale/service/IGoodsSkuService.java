package com.clothSale.service;


import com.clothSale.model.GoodsSku;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IGoodsSkuService {
    int deleteByPrimaryKey(String skuId);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    GoodsSku selectByPrimaryKey(String skuId);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);

    List<HashMap<String,Object>> selectSkuBySpecSpu(List<String> list, int size, String spu_id);

    List<HashMap<String,Object>> selectAllSku(String spu_id,int pageIndex,int pageSize);

    int insertGoodsSkuSpecValue(String sku_id, String spec_value_id, Date gmt_create, Date gmt_update);
}