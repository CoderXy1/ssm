package com.clothSale.service;

import com.clothSale.model.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IOrderInfoService {
    int deleteByPrimaryKey(String orderInfoId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderInfoId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<HashMap<String,Object>> selectAllOrderInfoByUserId(String user_id,String user_name,int order_state,int pageIndex,int pageSize);

    HashMap<String,Object> selectAllOrderInfoNumByUserId(String user_id,String user_name,int order_state);
    
}