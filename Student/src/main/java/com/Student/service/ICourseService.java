package com.Student.service;

import com.Student.model.Course;
import com.Student.model.CourseTeaDep;

import java.util.HashMap;
import java.util.List;

public interface ICourseService {

    int deleteByPrimaryKey(String courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<HashMap<String,Object>> selectCourse(String courseName, String teaId, int depId, int pageIndex, int pageSize);

    int selectCourseNum(String courseName, String teaId, int depId);

}
