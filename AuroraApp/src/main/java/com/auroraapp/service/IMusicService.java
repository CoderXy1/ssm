package com.auroraapp.service;

import com.auroraapp.model.Music;

import java.util.List;
import java.util.Map;

public interface IMusicService {
    int deleteByPrimaryKey(String musicid);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(String musicid);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    List<Map<String,Object>> selectAll(int pageIndex, int pageSize);

    Map<String,Object> selectSingle(String journalId);
}