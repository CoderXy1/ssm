package com.clothSale.service.impl;

import com.clothSale.mapper.MemberUserinfoMapper;
import com.clothSale.model.MemberUserinfo;
import com.clothSale.service.IMemberUserinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class MemberUserinfoServiceImpl implements IMemberUserinfoService {
    @Resource
    private MemberUserinfoMapper memberUserinfoMapper;

    @Override
    public int deleteByPrimaryKey(String memberId) {
        return memberUserinfoMapper.deleteByPrimaryKey(memberId);
    }

    @Override
    public int insert(MemberUserinfo record) {
        return memberUserinfoMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberUserinfo record) {
        return memberUserinfoMapper.insertSelective(record);
    }

    @Override
    public MemberUserinfo selectByPrimaryKey(String memberId) {
        return memberUserinfoMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberUserinfo record) {
        return memberUserinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberUserinfo record) {
        return memberUserinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectUserinfoByLogin(String user_name, String user_password) {
        return memberUserinfoMapper.selectUserinfoByLogin(user_name, user_password);
    }

    @Override
    public List<HashMap<String, Object>> selectAllUserinfo(int pageIndex, int pageSize, String user_name) {
        return memberUserinfoMapper.selectAllUserinfo(pageIndex, pageSize, user_name);
    }

    @Override
    public HashMap<String, Object> selectAllUserinfoNum(String user_name) {
        return memberUserinfoMapper.selectAllUserinfoNum(user_name);
    }

    @Override
    public List<HashMap<String, Object>> selectUserinfoByUserId(String user_id) {
        return memberUserinfoMapper.selectUserinfoByUserId(user_id);
    }

    @Override
    public int selectSameName(String user_name) {
        return memberUserinfoMapper.selectSameName(user_name);
    }
}
