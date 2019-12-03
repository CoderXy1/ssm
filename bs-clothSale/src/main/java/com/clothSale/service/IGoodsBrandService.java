package com.clothSale.service;



import com.clothSale.model.GoodsBrand;

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