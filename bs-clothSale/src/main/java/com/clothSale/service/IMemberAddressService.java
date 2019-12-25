package com.clothSale.service;

import com.clothSale.model.MemberAddress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IMemberAddressService {
    int deleteByPrimaryKey(String addressId);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    MemberAddress selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);

    List<HashMap<String,Object>> selectAllMemberAddress(String user_id,String user_name,int pageIndex,int pageSize);

    HashMap<String, Object> selectAllMemberAddressNum(String user_id,String user_name);

    HashMap<String, Object> selectSingleMemberAddress(String address_id);

    int insertMemberUserAddress(String address_id,String user_id,Date gmt_create);
    
}