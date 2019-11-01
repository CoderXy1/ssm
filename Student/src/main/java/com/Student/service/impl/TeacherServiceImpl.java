package com.Student.service.impl;

import com.Student.mapper.TeacherMapper;
import com.Student.model.Teacher;
import com.Student.model.TeacherDep;
import com.Student.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public int deleteByPrimaryKey(String teaid) {
        return this.teacherMapper.deleteByPrimaryKey(teaid);
    }

    @Override
    public int insert(Teacher record) {
        return this.teacherMapper.insert(record);
    }

    @Override
    public int insertSelective(Teacher record) {
        return this.teacherMapper.insertSelective(record);
    }

    @Override
    public Teacher selectByPrimaryKey(String teaid) {
        return this.teacherMapper.selectByPrimaryKey(teaid);
    }

    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return this.teacherMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Teacher record) {
        return this.teacherMapper.updateByPrimaryKey(record);
    }

    @Override
    public TeacherDep selectTeaByLogin(String id, String password) {
        return this.teacherMapper.selectTeaByLogin(id, password);
    }

    @Override
    public List<Teacher> selectAllTeacher(int depId) {
        return this.teacherMapper.selectAllTeacher(depId);
    }
}
