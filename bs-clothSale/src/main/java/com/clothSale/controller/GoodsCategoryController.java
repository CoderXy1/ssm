package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsCategory;
import com.clothSale.service.IGoodsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/GoodsCategory")
public class GoodsCategoryController {

    @Resource
    private IGoodsCategoryService goodsCategoryService;

    @RequestMapping("/selectGoodsCategory")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String,Object>>> selectGoodsCategory(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String categoryName){

        RequsetData<List<HashMap<String,Object>>> res = new RequsetData<>();

        List<HashMap<String,Object>> list = goodsCategoryService.selectGoodsCategory(pageIndex, pageSize, categoryName);
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

    @RequestMapping("/insertGoodsCategory")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<Integer> insertGoodsCategory(@RequestParam("category_id") String category_id, @RequestParam("category_name") String category_name, @RequestParam("category_order") int category_order){

        RequsetData<Integer> res = new RequsetData<>();

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setCategoryId(category_id);
        goodsCategory.setCategoryName(category_name);
        goodsCategory.setCategoryOrder(category_order);
        goodsCategory.setGmtCreate(new Date());

        int count = goodsCategoryService.insertSelective(goodsCategory);

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
