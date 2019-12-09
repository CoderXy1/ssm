package com.clothSale.service.impl;


import com.clothSale.mapper.GoodsCategoryMapper;
import com.clothSale.model.GoodsCategory;
import com.clothSale.service.IGoodsCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<HashMap<String, Object>> selectGoodsCategory(int pageIndex, int pageSize, String categoryName,String category_first_id) {
        return goodsCategoryMapper.selectGoodsCategory(pageIndex, pageSize, categoryName,category_first_id);
    }

    @Override
    public int insertCategoryFirst(String categoryFirstId, String categoryFirstName, int categoryFirstOrder, Date gmtCreate, Date gmtUpdate) {
        return goodsCategoryMapper.insertCategoryFirst(categoryFirstId, categoryFirstName, categoryFirstOrder, gmtCreate, gmtUpdate);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsCategoryFirst(int pageIndex, int pageSize, String categoryFirstName) {
        return goodsCategoryMapper.selectGoodsCategoryFirst(pageIndex, pageSize, categoryFirstName);
    }

    @Override
    public int insertCategoryConnect(String category_first_id, String category_id, Date gmt_create, Date gmt_update) {
        return goodsCategoryMapper.insertCategoryConnect(category_first_id, category_id, gmt_create, gmt_update);
    }
}
