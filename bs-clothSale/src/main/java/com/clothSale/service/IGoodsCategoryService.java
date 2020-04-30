package com.clothSale.service;

import com.clothSale.model.GoodsCategory;
import org.apache.ibatis.annotations.Param;

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

    int insertCategoryConnect(String category_first_id, String category_id, Date gmt_create,Date gmt_update);

    int insertCategoryFirst(String categoryFirstId, String categoryFirstName, int categoryFirstOrder, Date gmtCreate,Date gmtUpdate);

    List<HashMap<String,Object>> selectGoodsCategory(int pageIndex, int pageSize, String categoryName,String category_first_id);

    List<HashMap<String,Object>> selectGoodsCategoryFirst(int pageIndex, int pageSize, String categoryFirstName);

    List<HashMap<String,Object>> selectCategorySpuTotal();

    int updateGoodsCategoryFirst(String category_first_id,String category_name_edit,Integer category_order_edit,Date gmt_update);

    int deleteGoodsCategoryFirst(String category_first_id);

}