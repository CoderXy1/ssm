package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsSku;
import com.clothSale.service.IGoodsSkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/GoodsSku")
public class GoodsSkuController {

    @Resource
    private IGoodsSkuService goodsSkuService;

    @RequestMapping("/selectGoodsSku")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSku(@RequestParam("specIds") List<String> specIds, @RequestParam("spu_id")String spu_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<String> list = new ArrayList<>();

        for (String item : specIds){
            String temp = item.replace("{","").replace("}","").split(":")[1];
            if (temp != null && temp != ""){
                list.add(temp.replaceAll("\"",""));
            }
        }

        List<HashMap<String, Object>> list1 = goodsSkuService.test(list,list.size(),spu_id);

        if (list1 != null) {
            res.setItem(list1);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/insertGoodsSku")
    @ResponseBody
    public RequsetData<Integer> insertGoodsSku(@RequestParam("sku_id")String sku_id, @RequestParam("price") BigDecimal price,@RequestParam("stock") int stock, @RequestParam("spu_id")String spu_id, @RequestParam("specIds") List<String> specIds) {

        RequsetData<Integer> res = new RequsetData<>();

        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuId(sku_id);
        goodsSku.setSpuId(spu_id);
        goodsSku.setPrice(price);
        goodsSku.setStock(stock);
        goodsSku.setGmtCreate(new Date());
        int count = goodsSkuService.insertSelective(goodsSku);

        List<String> list = new ArrayList<>();

        for (String item : specIds){
            String temp = item.replace("{","").replace("}","").split(":")[1];
            if (temp != null && temp != ""){
                goodsSkuService.insertGoodsSkuSpecValue(sku_id,temp.replaceAll("\"",""),new Date(),null);
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
