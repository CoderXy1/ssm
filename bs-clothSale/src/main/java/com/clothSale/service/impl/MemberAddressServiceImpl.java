package com.clothSale.service.impl;

import com.clothSale.mapper.MemberAddressMapper;
import com.clothSale.model.MemberAddress;
import com.clothSale.service.IMemberAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberAddressServiceImpl implements IMemberAddressService {

    @Resource
    private MemberAddressMapper memberAddressMapper;

    @Override
    public int deleteByPrimaryKey(String addressId) {
        return memberAddressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public int insert(MemberAddress record) {
        return memberAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberAddress record) {
        return memberAddressMapper.insertSelective(record);
    }

    @Override
    public MemberAddress selectByPrimaryKey(String addressId) {
        return memberAddressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberAddress record) {
        return memberAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberAddress record) {
        return memberAddressMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectAllMemberAddress(String user_id,String user_name, int pageIndex, int pageSize) {
        return memberAddressMapper.selectAllMemberAddress(user_id,user_name, pageIndex, pageSize);
    }

    @Override
    public HashMap<String, Object> selectAllMemberAddressNum(String user_id,String user_name) {
        return memberAddressMapper.selectAllMemberAddressNum(user_id,user_name);
    }

    @Override
    public int insertMemberUserAddress(String address_id, String user_id, Date gmt_create) {
        return memberAddressMapper.insertMemberUserAddress(address_id, user_id, gmt_create);
    }

    @Override
    public HashMap<String, Object> selectSingleMemberAddress(String address_id) {
        return memberAddressMapper.selectSingleMemberAddress(address_id);
    }
}
