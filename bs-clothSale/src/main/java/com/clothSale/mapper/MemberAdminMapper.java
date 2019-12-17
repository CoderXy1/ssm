package com.clothSale.mapper;

import com.clothSale.model.MemberAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MemberAdminMapper {
    int deleteByPrimaryKey(String adminId);

    int insert(MemberAdmin record);

    int insertSelective(MemberAdmin record);

    MemberAdmin selectByPrimaryKey(String adminId);

    int updateByPrimaryKeySelective(MemberAdmin record);

    int updateByPrimaryKey(MemberAdmin record);

    List<HashMap<String,Object>> selectAdminByLogin(@Param("admin_name") String admin_name,@Param("admin_password") String admin_password);

    List<HashMap<String,Object>> selectAllAdmin(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,@Param("admin_name") String admin_name);

    HashMap<String,Object> selectAllAdminNum(@Param("admin_name") String admin_name);

}