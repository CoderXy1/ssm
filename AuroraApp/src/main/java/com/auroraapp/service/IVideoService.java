package com.auroraapp.service;

import com.auroraapp.model.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IVideoService {
    int deleteByPrimaryKey(String videoid);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(String videoid);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Map<String,Object>> selectAll(int pageIndex,int pageSize);

    Map<String,Object> selectSingle(String videoId);
}