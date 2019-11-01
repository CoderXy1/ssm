package com.Student.mapper;

import com.Student.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(@Param("depid") Integer depid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer depid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAllDep();
}