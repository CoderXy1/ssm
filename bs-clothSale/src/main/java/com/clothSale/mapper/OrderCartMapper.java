package com.clothSale.mapper;

import com.clothSale.model.OrderCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface OrderCartMapper {
    int deleteByPrimaryKey(String cartId);

    int insert(OrderCart record);

    int insertSelective(OrderCart record);

    OrderCart selectByPrimaryKey(String cartId);

    int updateByPrimaryKeySelective(OrderCart record);

    int updateByPrimaryKey(OrderCart record);

    List<HashMap<String,Object>> selectCartByUserId(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize,@Param("user_id") String user_id);

    HashMap<String,Object> selectCartNumByUserId(@Param("user_id") String user_id);

}