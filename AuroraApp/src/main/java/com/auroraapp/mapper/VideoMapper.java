package com.auroraapp.mapper;

import com.auroraapp.model.Video;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VideoMapper {
    int deleteByPrimaryKey(String videoid);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(String videoid);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Map<String,Object>> selectAll(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    Map<String,Object> selectSingle(@Param("videoId")String videoId);
}