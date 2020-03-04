package com.clothSale.mapper;

import com.clothSale.model.GoodsSpu;
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

    HashMap<String, Object> selectSingleGoodsSpu(@Param("spu_id")String spu_id);

    List<HashMap<String,Object>> selectGoodsSpu(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("category_id") String category_id, @Param("brand_id") String brand_id,@Param("goods_name")String goods_name);

    List<HashMap<String,Object>> selectGoodsSpuRand(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("category_id") String category_id, @Param("brand_id") String brand_id,@Param("goods_name")String goods_name);

    HashMap<String, Object> selectGoodsSpuNum(@Param("category_id")String category_id,@Param("brand_id") String brand_id,@Param("goods_name")String goods_name);
}