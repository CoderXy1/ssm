package com.angular.service.impl;

import com.angular.mapper.GoodsBrandMapper;
import com.angular.mapper.GoodsSpuMapper;
import com.angular.model.GoodsBrand;
import com.angular.model.GoodsSpu;
import com.angular.service.IGoodsBrandService;
import com.angular.service.IGoodsSpuService;
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
    public List<HashMap<String, Object>> selectGoodsSpu(int pageIndex, int pageSize, String category_id, String brand_id) {
        return goodsSpuMapper.selectGoodsSpu(pageIndex, pageSize, category_id, brand_id);
    }
}
