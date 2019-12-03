package com.clothSale.service.impl;


import com.clothSale.mapper.GoodsCategoryMapper;
import com.clothSale.model.GoodsCategory;
import com.clothSale.service.IGoodsCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public int deleteByPrimaryKey(String categoryId) {
        return goodsCategoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public int insert(GoodsCategory record) {
        return goodsCategoryMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsCategory record) {
        return goodsCategoryMapper.insertSelective(record);
    }

    @Override
    public GoodsCategory selectByPrimaryKey(String categoryId) {
        return goodsCategoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsCategory record) {
        return goodsCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsCategory record) {
        return goodsCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsCategory(int pageIndex, int pageSize, String categoryName) {
        return goodsCategoryMapper.selectGoodsCategory(pageIndex, pageSize, categoryName);
    }
}
