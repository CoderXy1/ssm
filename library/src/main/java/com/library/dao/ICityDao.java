package com.library.dao;

import com.library.model.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICityDao {

    List<City> selectAllCity();

    List<City> selectCityByProvinceId(@Param("provinceId") int provinceId);

}
