package com.clothSale.service.impl;

import com.clothSale.mapper.OrderCartMapper;
import com.clothSale.model.OrderCart;
import com.clothSale.service.IOrderCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderCartServiceImpl implements IOrderCartService {

    @Resource
    private OrderCartMapper orderCartMapper;

    @Override
    public int deleteByPrimaryKey(String cartId) {
        return orderCartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public int insert(OrderCart record) {
        return orderCartMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderCart record) {
        return orderCartMapper.insertSelective(record);
    }

    @Override
    public OrderCart selectByPrimaryKey(String cartId) {
        return orderCartMapper.selectByPrimaryKey(cartId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderCart record) {
        return orderCartMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderCart record) {
        return orderCartMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectCartByUserId(int pageIndex, int pageSize, String user_id) {
        return orderCartMapper.selectCartByUserId(pageIndex, pageSize, user_id);
    }

    @Override
    public HashMap<String, Object> selectCartNumByUserId(String user_id) {
        return orderCartMapper.selectCartNumByUserId(user_id);
    }
}
