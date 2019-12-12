package com.clothSale.service.impl;

import com.clothSale.mapper.MemberAdminMapper;
import com.clothSale.model.MemberAdmin;
import com.clothSale.service.IMemberAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberAdminServiceImpl implements IMemberAdminService {

    @Resource
    private MemberAdminMapper memberAdminMapper;

    @Override
    public int deleteByPrimaryKey(String adminId) {
        return memberAdminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public int insert(MemberAdmin record) {
        return memberAdminMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberAdmin record) {
        return memberAdminMapper.insertSelective(record);
    }

    @Override
    public MemberAdmin selectByPrimaryKey(String adminId) {
        return memberAdminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberAdmin record) {
        return memberAdminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberAdmin record) {
        return memberAdminMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String,Object>> selectAdminByLogin(String admin_name, String admin_password) {
        return memberAdminMapper.selectAdminByLogin(admin_name, admin_password);
    }
}
