package com.angular.mapper;

import com.angular.model.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(String categoryId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    List<HashMap<String,Object>> selectGoodsCategory(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("categoryName") String categoryName);
}