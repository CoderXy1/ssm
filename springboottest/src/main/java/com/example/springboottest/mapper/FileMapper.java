package com.example.springboottest.mapper;

import com.example.springboottest.model.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int deleteByPrimaryKey(String fileid);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String fileid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}