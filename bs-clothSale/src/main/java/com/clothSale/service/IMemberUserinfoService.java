package com.clothSale.service;

import com.clothSale.model.MemberUserinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IMemberUserinfoService {

    int deleteByPrimaryKey(String memberId);

    int insert(MemberUserinfo record);

    int insertSelective(MemberUserinfo record);

    MemberUserinfo selectByPrimaryKey(String memberId);

    int updateByPrimaryKeySelective(MemberUserinfo record);

    int updateByPrimaryKey(MemberUserinfo record);

    List<HashMap<String,Object>> selectUserinfoByLogin(String user_name,String user_password);

    List<HashMap<String,Object>> selectAllUserinfo(int pageIndex,int pageSize,String user_name);

    HashMap<String,Object> selectAllUserinfoNum(String user_name);
}