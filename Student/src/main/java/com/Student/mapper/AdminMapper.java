package com.Student.mapper;

import com.Student.model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(String adminid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String adminid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectAdminByLogin(@Param("id") String id, @Param("password") String password);
}