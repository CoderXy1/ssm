package com.Student.service;

import com.Student.model.Department;

import java.util.List;

public interface IDepartmentService {

    int deleteByPrimaryKey(Integer depid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer depid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAllDep();

}
