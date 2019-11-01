package com.library.service;

import com.library.model.City;

import java.util.List;

public interface ICityService {

    List<City> selectAllCity();

    List<City> selectCityByProvinceId(int provinceId);

}
