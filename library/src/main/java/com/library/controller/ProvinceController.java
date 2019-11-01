package com.library.controller;

import com.library.model.City;
import com.library.model.Province;
import com.library.service.ICityService;
import com.library.service.IProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/province")
public class ProvinceController {

    @Resource
    private IProvinceService provinceService;

    @RequestMapping("/selectAllProvince")
    @ResponseBody
    public List<Province> selectAllProvince() throws Exception{

        List<Province> list= this.provinceService.selectAllProvince();

        return list;
    }



}
