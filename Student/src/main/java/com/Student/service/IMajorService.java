package com.Student.service;

import com.Student.model.Major;

import java.util.List;

public interface IMajorService {

    int deleteByPrimaryKey(Integer majorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    List<Major> selectMajorByDepId(int depId);

}
