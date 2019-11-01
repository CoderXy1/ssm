package com.Student.mapper;

import com.Student.model.Teacher;
import com.Student.model.TeacherDep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(String teaid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teaid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    TeacherDep selectTeaByLogin(@Param("id") String id, @Param("password") String password);

    List<Teacher> selectAllTeacher(@Param("depId")int depId);
}