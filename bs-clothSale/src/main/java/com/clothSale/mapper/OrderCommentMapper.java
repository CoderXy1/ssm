package com.clothSale.mapper;

import com.clothSale.model.OrderComment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OrderCommentMapper {
    int deleteByPrimaryKey(String commentId);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    OrderComment selectByPrimaryKey(String commentId);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);

    List<HashMap<String,Object>> selectCommentBySpuId(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("spu_id") String spu_id);

    HashMap<String,Object> selectCommentNumBySpuId(@Param("spu_id") String spu_id);
}