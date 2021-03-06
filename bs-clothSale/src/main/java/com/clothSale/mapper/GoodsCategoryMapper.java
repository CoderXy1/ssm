package com.clothSale.mapper;


import com.clothSale.model.GoodsCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(String categoryId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    int insertCategoryFirst(@Param("categoryFirstId")String categoryFirstId, @Param("categoryFirstName")String categoryFirstName, @Param("categoryFirstOrder")int categoryFirstOrder, @Param("gmtCreate")Date gmtCreate, @Param("gmtUpdate")Date gmtUpdate);

    int insertCategoryConnect(@Param("category_first_id") String category_first_id,@Param("category_id") String category_id,@Param("gmt_create") Date gmt_create,@Param("gmt_update")Date gmt_update);

    List<HashMap<String,Object>> selectGoodsCategory(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("categoryName") String categoryName,@Param("category_first_id")String category_first_id);

    List<HashMap<String,Object>> selectGoodsCategoryFirst(@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize,@Param("categoryFirstName") String categoryFirstName);

    List<HashMap<String,Object>> selectCategorySpuTotal();

    int updateGoodsCategoryFirst(@Param("category_first_id") String category_first_id, @Param("category_name_edit") String category_name_edit, @Param("category_order_edit") Integer category_order_edit,@Param("gmt_update")Date gmt_update);

    int deleteGoodsCategoryFirst(@Param("category_first_id")String category_first_id);
}