package com.clothSale.service;

import com.clothSale.model.OrderComment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IOrderCommentService {

    int deleteByPrimaryKey(String commentId);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    OrderComment selectByPrimaryKey(String commentId);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);

    List<HashMap<String,Object>> selectCommentBySpuId(int pageIndex,int pageSize,String spu_id);

    HashMap<String,Object> selectCommentNumBySpuId(String spu_id);
}
