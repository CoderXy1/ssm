package com.Student.service.impl;

import com.Student.mapper.DepartmentMapper;
import com.Student.model.Department;
import com.Student.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Integer depid) {
        return this.departmentMapper.deleteByPrimaryKey(depid);
    }

    @Override
    public int insert(Department record) {
        return this.departmentMapper.insert(record);
    }

    @Override
    public int insertSelective(Department record) {
        return this.departmentMapper.insertSelective(record);
    }

    @Override
    public Department selectByPrimaryKey(Integer depid) {
        return this.departmentMapper.selectByPrimaryKey(depid);
    }

    @Override
    public int updateByPrimaryKeySelective(Department record) {
        return this.departmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return this.departmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Department> selectAllDep() {
        return this.departmentMapper.selectAllDep();
    }
}
