package com.clothSale.service.impl;

import com.clothSale.mapper.ActivityInfoMapper;
import com.clothSale.model.ActivityInfo;
import com.clothSale.service.IActivityInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ActivityInfoServiceImpl implements IActivityInfoService {

    @Resource
    private ActivityInfoMapper activityInfoMapper;

    @Override
    public int deleteByPrimaryKey(String activityId) {
        return activityInfoMapper.deleteByPrimaryKey(activityId);
    }

    @Override
    public int insert(ActivityInfo record) {
        return activityInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ActivityInfo record) {
        return activityInfoMapper.insertSelective(record);
    }

    @Override
    public ActivityInfo selectByPrimaryKey(String activityId) {
        return activityInfoMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public int updateByPrimaryKeySelective(ActivityInfo record) {
        return activityInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActivityInfo record) {
        return activityInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectActivityInfo(int pageIndex, int pageSize, String activity_name, int activity_state) {
        return activityInfoMapper.selectActivityInfo(pageIndex, pageSize, activity_name, activity_state);
    }

    @Override
    public List<HashMap<String, Object>> selectSpuAndActivity(int pageIndex, int pageSize, String activity_id,String goods_name) {
        return activityInfoMapper.selectSpuAndActivity(pageIndex, pageSize, activity_id,goods_name);
    }

    @Override
    public HashMap<String,Object> selectNumActivityInfo(String activity_name, int activity_state) {
        return activityInfoMapper.selectNumActivityInfo(activity_name, activity_state);
    }

    @Override
    public HashMap<String,Object> selectNumSpuAndActivity(String activity_id,String goods_name) {
        return activityInfoMapper.selectNumSpuAndActivity(activity_id,goods_name);
    }

    @Override
    public List<HashMap<String, Object>> selectSpuOfActivity(int pageIndex, int pageSize, String activity_id) {
        return activityInfoMapper.selectSpuOfActivity(pageIndex, pageSize, activity_id);
    }

    @Override
    public HashMap<String, Object> selectNumSpuOfActivity(String activity_id) {
        return activityInfoMapper.selectNumSpuOfActivity(activity_id);
    }

    @Override
    public int deleteActivitySpu(String activity_id, String spu_id) {
        return activityInfoMapper.deleteActivitySpu(activity_id, spu_id);
    }

    @Override
    public int insertActivitySpu(String activity_id, String spu_id, Date gmt_create) {
        return activityInfoMapper.insertActivitySpu(activity_id, spu_id, gmt_create);
    }
}
