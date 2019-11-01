package com.Student.service.impl;

import com.Student.mapper.CourseMapper;
import com.Student.model.Course;
import com.Student.model.CourseTeaDep;
import com.Student.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public int deleteByPrimaryKey(String courseid) {
        return this.courseMapper.deleteByPrimaryKey(courseid);
    }

    @Override
    public int insert(Course record) {
        return this.courseMapper.insert(record);
    }

    @Override
    public int insertSelective(Course record) {
        return this.courseMapper.insertSelective(record);
    }

    @Override
    public Course selectByPrimaryKey(String courseid) {
        return this.courseMapper.selectByPrimaryKey(courseid);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record) {
        return this.courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        return this.courseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String,Object>> selectCourse(String courseName, String teaId, int depId, int pageIndex, int pageSize) {
        return this.courseMapper.selectCourse(courseName, teaId, depId,pageIndex,pageSize);
    }

    @Override
    public int selectCourseNum(String courseName, String teaId, int depId) {
        return this.courseMapper.selectCourseNum(courseName, teaId, depId);
    }
}
