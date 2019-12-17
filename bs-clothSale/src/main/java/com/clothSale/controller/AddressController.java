package com.clothSale.controller;


import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.service.IAddressAreasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Resource
    private IAddressAreasService addressAreasService;

    @RequestMapping("/selectAllProvinces")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectAllProvinces() {

        List<HashMap<String, Object>> list = addressAreasService.selectAllProvinces();

        return setRequsetData(list);
    }

    @RequestMapping("/selectCityByProvinceId")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectCityByProvinceId(@RequestParam("province_id") int province_id) {

        List<HashMap<String, Object>> list = addressAreasService.selectCityByProvinceId(province_id);

        return setRequsetData(list);
    }

    @RequestMapping("/selectAreaByCityId")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectAreaByCityId(@RequestParam("city_id") int city_id) {

        List<HashMap<String, Object>> list = addressAreasService.selectAreaByCityId(city_id);

        return setRequsetData(list);
    }

    @RequestMapping("/selectInfoByAreaId")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectInfoByAreaId(@RequestParam("area_id") int area_id) {

        List<HashMap<String, Object>> list = addressAreasService.selectInfoByAreaId(area_id);

        return setRequsetData(list);
    }

    private RequsetData<List<HashMap<String, Object>>> setRequsetData(List<HashMap<String, Object>> list) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();
        if (!list.isEmpty()) {
            res.setItem(list);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }
        return res;
    }


}
