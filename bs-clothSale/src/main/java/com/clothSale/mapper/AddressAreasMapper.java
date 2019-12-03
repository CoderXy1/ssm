package com.clothSale.mapper;

import com.clothSale.model.AddressAreas;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AddressAreasMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressAreas record);

    int insertSelective(AddressAreas record);

    AddressAreas selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressAreas record);

    int updateByPrimaryKey(AddressAreas record);

    List<HashMap<String, Object>> selectAllProvinces();

    List<HashMap<String, Object>> selectCityByProvinceId(@Param("province_id") int province_id);

    List<HashMap<String, Object>> selectAreaByCityId(@Param("city_id") int city_id);

    List<HashMap<String, Object>> selectInfoByAreaId(@Param("area_id") int area_id);
}