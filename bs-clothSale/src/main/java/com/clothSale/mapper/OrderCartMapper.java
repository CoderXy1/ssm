package com.clothSale.mapper;

import com.clothSale.model.OrderCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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

    int insertOrderCollect(@Param("collect_id")String collect_id,@Param("user_id") String user_id,@Param("spu_id") String spu_id,@Param("gmt_create") Date gmt_create);

    int deleteOrderCollect(@Param("collect_id")String collect_id);

    List<HashMap<String,Object>> selectOrderCollect(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize,@Param("user_id") String user_id);

    HashMap<String,Object> selectOrderCollectNum(@Param("user_id") String user_id);

    List<HashMap<String,Object>> selectOrderCollectBySpuId(@Param("spu_id")String spu_id,@Param("user_id")String user_id);
}