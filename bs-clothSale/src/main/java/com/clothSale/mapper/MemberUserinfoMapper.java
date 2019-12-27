package com.clothSale.mapper;

import com.clothSale.model.MemberUserinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MemberUserinfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(MemberUserinfo record);

    int insertSelective(MemberUserinfo record);

    MemberUserinfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(MemberUserinfo record);

    int updateByPrimaryKey(MemberUserinfo record);

    List<HashMap<String,Object>> selectUserinfoByUserId(@Param("user_id") String user_id);

    List<HashMap<String,Object>> selectUserinfoByLogin(@Param("user_name") String user_name, @Param("user_password") String user_password);

    List<HashMap<String,Object>> selectAllUserinfo(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,@Param("user_name") String user_name);

    HashMap<String,Object> selectAllUserinfoNum(@Param("user_name") String user_name);
}