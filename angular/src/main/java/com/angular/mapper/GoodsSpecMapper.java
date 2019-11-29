package com.angular.mapper;

import com.angular.model.GoodsSpec;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface GoodsSpecMapper {
    int deleteByPrimaryKey(String specId);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    GoodsSpec selectByPrimaryKey(String specId);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);

    List<HashMap<String,Object>> selectGoodsSpecAndValue(@Param("spu_id") String spu_id);

    List<HashMap<String,Object>> selectAllGoodsSpec(@Param("category_id") String category_id);

    List<HashMap<String,Object>> selectGoodsSpecValue(@Param("spec_id")String spec_id);

    String selectBySpecName(@Param("spec_name")String spec_name);

    int insertGoodsSpecSpu(@Param("spu_id")String spu_id, @Param("spec_id")String spec_id, @Param("gmt_create")Date gmt_create,@Param("gmt_update") Date gmt_update);

    int insertGoodsSpecCategory(@Param("spec_id")String spec_id,@Param("category_id")String category_id,@Param("gmt_create")Date gmt_create,@Param("gmt_update")Date gmt_update);

    int insertGoodsSpecValue( @Param("spec_value_id")String spec_value_id,@Param("spec_id")String spec_id,@Param("spec_value")String spec_value,@Param("gmt_create")Date gmt_create,@Param("gmt_update") Date gmt_update);

}