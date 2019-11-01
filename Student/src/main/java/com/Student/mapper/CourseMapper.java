package com.Student.mapper;

import com.Student.model.Course;
import com.Student.model.CourseTeaDep;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<HashMap<String,Object>> selectCourse(@Param("courseName") String courseName, @Param("teaId") String teaId, @Param("depId") int depId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    int selectCourseNum(@Param("courseName") String courseName, @Param("teaId") String teaId, @Param("depId") int depId);
}