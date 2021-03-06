package com.clothSale.service;


import com.clothSale.model.GoodsSpu;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IGoodsSpuService {
    int deleteByPrimaryKey(String spuId);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(String spuId);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);

    HashMap<String, Object> selectSingleGoodsSpu(String spu_id);

    List<HashMap<String,Object>> selectGoodsSpu(int pageIndex, int pageSize, String category_id, String brand_id,String goods_name);

    List<HashMap<String,Object>> selectGoodsSpuRand(int pageIndex, int pageSize, String category_id, String brand_id,String goods_name);

    HashMap<String, Object> selectGoodsSpuNum(String category_id, String brand_id,String goods_name);

}