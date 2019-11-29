package com.angular.mapper;

import com.angular.model.GoodsBrand;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface GoodsBrandMapper {
    int deleteByPrimaryKey(String brandId);

    int insert(GoodsBrand record);

    int insertSelective(GoodsBrand record);

    GoodsBrand selectByPrimaryKey(String brandId);

    int updateByPrimaryKeySelective(GoodsBrand record);

    int updateByPrimaryKey(GoodsBrand record);

    List<HashMap<String,Object>> selectGoodsBrand(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("brandName") String brandName);
}