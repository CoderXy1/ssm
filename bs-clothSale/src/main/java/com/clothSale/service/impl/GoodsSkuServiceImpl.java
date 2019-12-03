package com.clothSale.service.impl;


import com.clothSale.mapper.GoodsSkuMapper;
import com.clothSale.model.GoodsSku;
import com.clothSale.service.IGoodsSkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsSkuServiceImpl implements IGoodsSkuService {

    @Resource
    private GoodsSkuMapper goodsSkuMapper;

    @Override
    public int deleteByPrimaryKey(String skuId) {
        return goodsSkuMapper.deleteByPrimaryKey(skuId);
    }

    @Override
    public int insert(GoodsSku record) {
        return goodsSkuMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsSku record) {
        return goodsSkuMapper.insertSelective(record);
    }

    @Override
    public GoodsSku selectByPrimaryKey(String skuId) {
        return goodsSkuMapper.selectByPrimaryKey(skuId);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsSku record) {
        return goodsSkuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsSku record) {
        return goodsSkuMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> test(List<String> list,int size,String spu_id) {
        return goodsSkuMapper.test(list,size,spu_id);
    }

    @Override
    public int insertGoodsSkuSpecValue(String sku_id, String spec_value_id, Date gmt_create, Date gmt_update) {
        return goodsSkuMapper.insertGoodsSkuSpecValue(sku_id, spec_value_id, gmt_create, gmt_update);
    }
}
