package com.clothSale.service;

import com.clothSale.model.OrderCart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IOrderCartService {
    int deleteByPrimaryKey(String cartId);

    int insert(OrderCart record);

    int insertSelective(OrderCart record);

    OrderCart selectByPrimaryKey(String cartId);

    int updateByPrimaryKeySelective(OrderCart record);

    int updateByPrimaryKey(OrderCart record);

    List<HashMap<String,Object>> selectCartByUserId(int pageIndex,int pageSize,String user_id);

    HashMap<String,Object> selectCartNumByUserId(String user_id);

    int insertOrderCollect(String collect_id,String user_id, String spu_id,Date gmt_create);

    int deleteOrderCollect(String collect_id);

    List<HashMap<String,Object>> selectOrderCollect(int pageIndex,int pageSize,String user_id);

    HashMap<String,Object> selectOrderCollectNum(String user_id);


}