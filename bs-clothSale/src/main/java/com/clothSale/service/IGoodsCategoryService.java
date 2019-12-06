package com.clothSale.service;

import com.clothSale.model.GoodsCategory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IGoodsCategoryService {
    int deleteByPrimaryKey(String categoryId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    int insertCategoryFirst(String categoryFirstId, String categoryFirstName, int categoryFirstOrder, Date gmtCreate,Date gmtUpdate);

    List<HashMap<String,Object>> selectGoodsCategory(int pageIndex, int pageSize, String categoryName);

    List<HashMap<String,Object>> selectGoodsCategoryFirst(int pageIndex, int pageSize, String categoryFirstName);
}