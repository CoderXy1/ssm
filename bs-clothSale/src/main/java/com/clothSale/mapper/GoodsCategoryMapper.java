package com.clothSale.mapper;


import com.clothSale.model.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(String categoryId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    int insertCategoryFirst(@Param("categoryFirstId")String categoryFirstId, @Param("categoryFirstName")String categoryFirstName, @Param("categoryFirstOrder")int categoryFirstOrder, @Param("gmtCreate")Date gmtCreate, @Param("gmtUpdate")Date gmtUpdate);

    List<HashMap<String,Object>> selectGoodsCategory(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("categoryName") String categoryName);

    List<HashMap<String,Object>> selectGoodsCategoryFirst(@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize,@Param("categoryFirstName") String categoryFirstName);
}