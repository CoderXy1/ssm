package com.clothSale.service;

import com.clothSale.model.ActivityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IActivityInfoService {

    int deleteByPrimaryKey(String activityId);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(String activityId);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);

    List<HashMap<String, Object>> selectActivityInfo(int pageIndex,int pageSize,String activity_name,int activity_state);

    HashMap<String,Object> selectNumActivityInfo(String activity_name,int activity_state);

    List<HashMap<String, Object>> selectSpuAndActivity(int pageIndex,int pageSize,String activity_id,String goods_name);

    HashMap<String,Object> selectNumSpuAndActivity(String activity_id,String goods_name);

    List<HashMap<String, Object>> selectSpuOfActivity(int pageIndex, int pageSize,String activity_id);

    HashMap<String,Object> selectNumSpuOfActivity(String activity_id);

    int deleteActivitySpu(String activity_id,String spu_id);

    int insertActivitySpu(String activity_id,String spu_id,Date gmt_create);

}
