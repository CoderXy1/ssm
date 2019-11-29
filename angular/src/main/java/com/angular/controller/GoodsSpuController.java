package com.angular.controller;

import com.angular.controller.jsonmodel.RequsetData;
import com.angular.model.GoodsSpu;
import com.angular.service.IGoodsBrandService;
import com.angular.service.IGoodsSpecService;
import com.angular.service.IGoodsSpuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/GoodsSpu")
public class GoodsSpuController {

    @Resource
    private IGoodsSpuService goodsSpuService;

    @Resource
    private IGoodsSpecService goodsSpecService;

    @RequestMapping("/selectGoodsSpu")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSpu(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String category_id, @RequestParam(required = false) String brand_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsSpuService.selectGoodsSpu(pageIndex, pageSize, category_id, brand_id);

        if (list != null) {
            res.setItem(list);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/insertGoodsSpu")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<Integer> insertGoodsSpu(@RequestParam("spu_id") String spu_id,@RequestParam("goods_name") String goods_name,
                                                                      @RequestParam("low_price") BigDecimal low_price,@RequestParam("spu_icon_id")String spu_icon_id,
                                                                      @RequestParam("category_id") String category_id,@RequestParam("brand_id")String brand_id,@RequestParam("specIds")List<String> specIds,@RequestParam(required = false)int spu_order) {

        RequsetData<Integer> res = new RequsetData<>();

        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setSpuId(spu_id);
        goodsSpu.setGoodsName(goods_name);
        goodsSpu.setLowPrice(low_price);
        goodsSpu.setSpuIconId(spu_icon_id);
        goodsSpu.setCategoryId(category_id);
        goodsSpu.setBrandId(brand_id);
        goodsSpu.setSpuOrder(spu_order);
        goodsSpu.setGmtCreate(new Date());

        int count = goodsSpuService.insertSelective(goodsSpu);

        for (String item : specIds){
            String temp = item.replace("{","").replace("}","").split(":")[0];
            String temp1 = item.replace("{","").replace("}","").split(":")[1];
            if (Boolean.valueOf(temp1)){
                goodsSpecService.insertGoodsSpecSpu(spu_id,temp.replaceAll("\"",""),new Date(),null);
            }
        }

        if (count == 1) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }


}
