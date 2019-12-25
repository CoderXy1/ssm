package com.clothSale.mapper;

import com.clothSale.model.MemberAddress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface MemberAddressMapper {
    int deleteByPrimaryKey(String addressId);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    MemberAddress selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);

    List<HashMap<String,Object>> selectAllMemberAddress(@Param("user_id") String user_id,@Param("user_name")String user_name,@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

    HashMap<String, Object> selectAllMemberAddressNum(@Param("user_id") String user_id,@Param("user_name")String user_name);

    HashMap<String, Object> selectSingleMemberAddress(@Param("address_id")String address_id);

    int insertMemberUserAddress(@Param("address_id")String address_id,@Param("user_id") String user_id,@Param("gmt_create") Date gmt_create);
}