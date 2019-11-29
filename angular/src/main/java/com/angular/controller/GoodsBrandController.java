package com.angular.controller;

import com.angular.controller.jsonmodel.RequsetData;
import com.angular.model.GoodsBrand;
import com.angular.service.IGoodsBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/GoodsBrand")
public class GoodsBrandController {

    @Resource
    private IGoodsBrandService goodsBrandService;

    @RequestMapping("/selectGoodsBrand")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String,Object>>> selectGoodsBrand(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String brandName){

        RequsetData<List<HashMap<String,Object>>> res = new RequsetData<>();

        List<HashMap<String,Object>> list = goodsBrandService.selectGoodsBrand(pageIndex, pageSize, brandName);

        if (list != null){
            res.setItem(list);
            res.setMsg("成功");
            res.setSuccess(true);
        }else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/insertGoodsBrand")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<Integer> insertGoodsBrand(@RequestParam("brand_id") String brand_id, @RequestParam("brand_name") String brand_name, @RequestParam("brand_order") int brand_order,@RequestParam("brand_icon_id") String brand_icon_id){

        RequsetData<Integer> res = new RequsetData<>();

        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setBrandId(brand_id);
        goodsBrand.setBrandName(brand_name);
        goodsBrand.setBrandIconId(brand_icon_id);
        goodsBrand.setBrandOrder(brand_order);
        goodsBrand.setGmtCreate(new Date());

        int count = goodsBrandService.insertSelective(goodsBrand);

        if (count == 1){
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        }else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }


}
