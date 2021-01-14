package com.auroraapp.mapper;

import com.auroraapp.model.Music;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MusicMapper {
    int deleteByPrimaryKey(String musicid);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(String musicid);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    List<Map<String,Object>> selectAll(@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

    Map<String,Object> selectSingle(@Param("journalId")String journalId);
}