package com.clothSale.service;


import com.clothSale.model.GoodsSpec;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IGoodsSpecService {
    int deleteByPrimaryKey(String specId);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    GoodsSpec selectByPrimaryKey(String specId);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);

    List<HashMap<String,Object>> selectGoodsSpecAndValue(String spu_id);

    List<HashMap<String,Object>> selectGoodsSpecValue(String spec_id);

    List<HashMap<String,Object>> selectAllGoodsSpec(String category_id);

    String selectBySpecName(String spec_name);

    int insertGoodsSpecSpu(String spu_id, String spec_id, Date gmt_create, Date gmt_update);

    int insertGoodsSpecCategory(String spec_id, String category_id, Date gmt_create, Date gmt_update);

    int insertGoodsSpecValue(String spec_value_id, String spec_id, String spec_value, Date gmt_create, Date gmt_update);
}