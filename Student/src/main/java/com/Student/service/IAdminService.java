package com.Student.service;

import com.Student.model.Admin;

public interface IAdminService {

    int deleteByPrimaryKey(String adminid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String adminid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectAdminByLogin(String id,String password);
}
