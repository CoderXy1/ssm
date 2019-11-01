package com.Student.service.impl;

import com.Student.mapper.AdminMapper;
import com.Student.model.Admin;
import com.Student.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public int deleteByPrimaryKey(String adminid) {
        return this.adminMapper.deleteByPrimaryKey(adminid);
    }

    @Override
    public int insert(Admin record) {
        return this.adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return this.adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(String adminid) {
        return this.adminMapper.selectByPrimaryKey(adminid);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return this.adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return this.adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public Admin selectAdminByLogin(String id, String password) {
        return this.adminMapper.selectAdminByLogin(id,password);
    }
}
