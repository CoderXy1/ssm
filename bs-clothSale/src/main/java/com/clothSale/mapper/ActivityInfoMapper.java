package com.clothSale.mapper;

import com.clothSale.model.ActivityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ActivityInfoMapper {
    int deleteByPrimaryKey(String activityId);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(String activityId);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);

    List<HashMap<String, Object>> selectActivityInfo(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,@Param("activity_name") String activity_name,@Param("activity_state") int activity_state);

    HashMap<String,Object> selectNumActivityInfo(@Param("activity_name") String activity_name,@Param("activity_state") int activity_state);

    List<HashMap<String, Object>> selectSpuAndActivity(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,@Param("activity_id") String activity_id,@Param("goods_name")String goods_name);

    HashMap<String,Object> selectNumSpuAndActivity(@Param("activity_id") String activity_id,@Param("goods_name")String goods_name);

    List<HashMap<String, Object>> selectSpuOfActivity(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,@Param("activity_id") String activity_id);

    HashMap<String,Object> selectNumSpuOfActivity(@Param("activity_id") String activity_id);

    int deleteActivitySpu(@Param("activity_id") String activity_id,@Param("spu_id")String spu_id);

    int insertActivitySpu(@Param("activity_id") String activity_id,@Param("spu_id")String spu_id,@Param("gmt_create") Date gmt_create);
}