package com.clothSale.service.impl;

import com.clothSale.mapper.OrderCommentMapper;
import com.clothSale.model.OrderComment;
import com.clothSale.service.IOrderCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderCommentServiceImpl implements IOrderCommentService {

    @Resource
    private OrderCommentMapper orderCommentMapper;

    @Override
    public int deleteByPrimaryKey(String commentId) {
        return orderCommentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public int insert(OrderComment record) {
        return orderCommentMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderComment record) {
        return orderCommentMapper.insertSelective(record);
    }

    @Override
    public OrderComment selectByPrimaryKey(String commentId) {
        return orderCommentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderComment record) {
        return orderCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderComment record) {
        return orderCommentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectCommentBySpuId(int pageIndex, int pageSize, String spu_id) {
        return orderCommentMapper.selectCommentBySpuId(pageIndex, pageSize, spu_id);
    }

    @Override
    public HashMap<String, Object> selectCommentNumBySpuId(String spu_id) {
        return orderCommentMapper.selectCommentNumBySpuId(spu_id);
    }
}
