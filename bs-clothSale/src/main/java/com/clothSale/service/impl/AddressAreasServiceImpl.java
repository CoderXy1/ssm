package com.clothSale.service.impl;


import com.clothSale.mapper.AddressAreasMapper;
import com.clothSale.service.IAddressAreasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class AddressAreasServiceImpl implements IAddressAreasService {

    @Resource
    private AddressAreasMapper addressAreasMapper;

    @Override
    public List<HashMap<String, Object>> selectAllProvinces() {
        return addressAreasMapper.selectAllProvinces();
    }

    @Override
    public List<HashMap<String, Object>> selectCityByProvinceId(int province_id) {
        return addressAreasMapper.selectCityByProvinceId(province_id);
    }

    @Override
    public List<HashMap<String, Object>> selectAreaByCityId(int city_id) {
        return addressAreasMapper.selectAreaByCityId(city_id);
    }

    @Override
    public List<HashMap<String, Object>> selectInfoByAreaId(int area_id) {
        return addressAreasMapper.selectInfoByAreaId(area_id);
    }
}
