package com.clothSale.mapper;

import com.clothSale.model.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(String orderInfoId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderInfoId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<HashMap<String,Object>> selectAllOrderInfoByUserId(@Param("user_id") String user_id,@Param("user_name")String user_name,@Param("order_state")int order_state,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);

    HashMap<String,Object> selectAllOrderInfoNumByUserId(@Param("user_id") String user_id,@Param("user_name")String user_name,@Param("order_state")int order_state);

    List<HashMap<String,Object>> selectAllOrderInfoNumByMonth();

}