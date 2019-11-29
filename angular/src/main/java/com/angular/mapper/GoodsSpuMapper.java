package com.angular.mapper;

import com.angular.model.GoodsSpu;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface GoodsSpuMapper {
    int deleteByPrimaryKey(String spuId);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(String spuId);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);

    List<HashMap<String,Object>> selectGoodsSpu(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("category_id") String category_id,@Param("brand_id") String brand_id);

}