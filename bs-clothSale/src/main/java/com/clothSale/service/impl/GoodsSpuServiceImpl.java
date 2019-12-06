package com.clothSale.service.impl;


import com.clothSale.mapper.GoodsSpuMapper;
import com.clothSale.model.GoodsSpu;
import com.clothSale.service.IGoodsSpuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsSpuServiceImpl implements IGoodsSpuService {

    @Resource
    private GoodsSpuMapper goodsSpuMapper;

    @Override
    public int deleteByPrimaryKey(String spuId) {
        return goodsSpuMapper.deleteByPrimaryKey(spuId);
    }

    @Override
    public int insert(GoodsSpu record) {
        return goodsSpuMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsSpu record) {
        return goodsSpuMapper.insertSelective(record);
    }

    @Override
    public GoodsSpu selectByPrimaryKey(String spuId) {
        return goodsSpuMapper.selectByPrimaryKey(spuId);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsSpu record) {
        return goodsSpuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsSpu record) {
        return goodsSpuMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsSpu(int pageIndex, int pageSize, String category_id, String brand_id,String goods_name) {
        return goodsSpuMapper.selectGoodsSpu(pageIndex, pageSize, category_id, brand_id,goods_name);
    }

    @Override
    public HashMap<String, Object> selectGoodsSpuNum(String category_id, String brand_id,String goods_name) {
        return goodsSpuMapper.selectGoodsSpuNum(category_id, brand_id,goods_name);
    }
}
