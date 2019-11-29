package com.angular.service;

import com.angular.model.GoodsCategory;

import java.util.HashMap;
import java.util.List;

public interface IGoodsCategoryService {
    int deleteByPrimaryKey(String categoryId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    List<HashMap<String,Object>> selectGoodsCategory(int pageIndex, int pageSize, String categoryName);
}