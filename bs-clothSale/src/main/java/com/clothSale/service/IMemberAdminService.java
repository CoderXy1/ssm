package com.clothSale.service;

import com.clothSale.model.MemberAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IMemberAdminService {
    int deleteByPrimaryKey(String adminId);

    int insert(MemberAdmin record);

    int insertSelective(MemberAdmin record);

    MemberAdmin selectByPrimaryKey(String adminId);

    int updateByPrimaryKeySelective(MemberAdmin record);

    int updateByPrimaryKey(MemberAdmin record);

    List<HashMap<String,Object>> selectAdminByLogin(String admin_name, String admin_password);

    List<HashMap<String,Object>> selectAllAdmin(int pageIndex,int pageSize,String admin_name);

    HashMap<String,Object> selectAllAdminNum(String admin_name);
}