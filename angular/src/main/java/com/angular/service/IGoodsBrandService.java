package com.angular.service;

import com.angular.model.GoodsBrand;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IGoodsBrandService {

    int deleteByPrimaryKey(String brandId);

    int insert(GoodsBrand record);

    int insertSelective(GoodsBrand record);

    GoodsBrand selectByPrimaryKey(String brandId);

    int updateByPrimaryKeySelective(GoodsBrand record);

    int updateByPrimaryKey(GoodsBrand record);

    List<HashMap<String,Object>> selectGoodsBrand(int pageIndex, int pageSize, String brandName);
}