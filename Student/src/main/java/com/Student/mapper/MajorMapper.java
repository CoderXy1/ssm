package com.Student.mapper;

import com.Student.model.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorMapper {
    int deleteByPrimaryKey(Integer majorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    List<Major> selectMajorByDepId(@Param("depId") int depId);
}