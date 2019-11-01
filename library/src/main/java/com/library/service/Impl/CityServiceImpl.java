package com.library.service.Impl;

import com.library.dao.ICityDao;
import com.library.model.City;
import com.library.service.ICityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cityService")
public class CityServiceImpl implements ICityService {

    @Resource
    private ICityDao cityDao;

    @Override
    public List<City> selectCityByProvinceId(int provinceId) {
        return this.cityDao.selectCityByProvinceId(provinceId);
    }

    @Override
    public List<City> selectAllCity() {
        return this.cityDao.selectAllCity();
    }
}
