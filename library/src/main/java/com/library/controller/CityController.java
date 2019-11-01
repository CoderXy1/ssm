package com.library.controller;

import com.library.model.Book;
import com.library.model.City;
import com.library.service.IBookService;
import com.library.service.ICityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {

    @Resource
    private ICityService cityService;

    @RequestMapping("/selectAllCity")
    @ResponseBody
    public List<City> selectAllCity() throws Exception{

        List<City> list= this.cityService.selectAllCity();

        return list;
    }

    @RequestMapping("/selectCityByProvinceId")
    @ResponseBody
    public List<City> selectCityByProvinceId(@RequestParam("provinceId") int provinceId) throws Exception{

        List<City> list= this.cityService.selectCityByProvinceId(provinceId);

        return list;
    }

}
