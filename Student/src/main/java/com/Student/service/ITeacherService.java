package com.Student.service;

import com.Student.model.Teacher;
import com.Student.model.TeacherDep;

import java.util.List;

public interface ITeacherService {

    int deleteByPrimaryKey(String teaid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teaid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    TeacherDep selectTeaByLogin(String id, String password);

    List<Teacher> selectAllTeacher(int depId);

}
