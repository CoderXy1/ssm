package com.clothSale.service;

import java.util.HashMap;
import java.util.List;

public interface IAddressAreasService {

    List<HashMap<String, Object>> selectAllProvinces();

    List<HashMap<String, Object>> selectCityByProvinceId(int province_id);

    List<HashMap<String, Object>> selectAreaByCityId(int city_id);

    List<HashMap<String, Object>> selectInfoByAreaId(int area_id);
}