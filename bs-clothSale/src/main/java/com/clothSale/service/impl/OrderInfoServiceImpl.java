package com.clothSale.service.impl;

import com.clothSale.mapper.OrderInfoMapper;
import com.clothSale.model.OrderInfo;
import com.clothSale.service.IOrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public int deleteByPrimaryKey(String orderInfoId) {
        return orderInfoMapper.deleteByPrimaryKey(orderInfoId);
    }

    @Override
    public int insert(OrderInfo record) {
        return orderInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return orderInfoMapper.insertSelective(record);
    }

    @Override
    public OrderInfo selectByPrimaryKey(String orderInfoId) {
        return orderInfoMapper.selectByPrimaryKey(orderInfoId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectAllOrderInfoByUserId(String user_id,String user_name, int order_state, int pageIndex, int pageSize) {
        return orderInfoMapper.selectAllOrderInfoByUserId(user_id,user_name, order_state, pageIndex, pageSize);
    }

    @Override
    public HashMap<String, Object> selectAllOrderInfoNumByUserId(String user_id,String user_name, int order_state) {
        return orderInfoMapper.selectAllOrderInfoNumByUserId(user_id,user_name, order_state);
    }

    @Override
    public List<HashMap<String, Object>> selectAllOrderInfoNumByMonth() {
        return orderInfoMapper.selectAllOrderInfoNumByMonth();
    }
}
