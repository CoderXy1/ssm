package com.clothSale.mapper;


import com.clothSale.model.GoodsSku;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface GoodsSkuMapper {
    int deleteByPrimaryKey(String skuId);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    GoodsSku selectByPrimaryKey(String skuId);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);

    List<HashMap<String,Object>> selectSkuBySpecSpu(@Param("list") List<String> list, @Param("size") int size, @Param("spu_id") String spu_id);

    List<HashMap<String,Object>> selectAllSku(@Param("spu_id")String spu_id,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);

    int insertGoodsSkuSpecValue(@Param("sku_id") String sku_id, @Param("spec_value_id") String spec_value_id, @Param("gmt_create") Date gmt_create, @Param("gmt_update") Date gmt_update);

}