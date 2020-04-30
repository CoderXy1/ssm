package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsSku;
import com.clothSale.service.IGoodsSkuService;
import org.json.JSONObject;
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

    @RequestMapping("/selectSkuBySpecSpu")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectSkuBySpecSpu(@RequestParam("specIds") String specIds, @RequestParam("spu_id")String spu_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<String> list = new ArrayList<>();

        for(String str:specIds.split(",")){
            if (!str.equals("null")){
                list.add(str);
            }
        }

        List<HashMap<String, Object>> list1 = new ArrayList<>();
        if (!list.isEmpty()){
            list1 = goodsSkuService.selectSkuBySpecSpu(list,list.size(),spu_id);
        }

        if (!list1.isEmpty()) {
            res.setItem(list1);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/selectAllSku")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectAllSku(@RequestParam("spu_id")String spu_id,@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize) {

        List<HashMap<String, Object>> list = goodsSkuService.selectAllSku(spu_id, pageIndex, pageSize);

        return setRequsetData(list,"查询");
    }

    @RequestMapping("/updateGoodsSku")
    @ResponseBody
    public RequsetData<Integer> updateGoodsSku(@RequestParam("sku_id")String sku_id,@RequestParam("stock")int stock) {

        RequsetData<Integer> res = new RequsetData<>();
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuId(sku_id);
        goodsSku.setStock(stock);

        int count = goodsSkuService.updateByPrimaryKeySelective(goodsSku);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("进货成功");
            res.setSuccess(true);
        } else {
            res.setMsg("进货失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/insertGoodsSku")
    @ResponseBody
    public RequsetData<Integer> insertGoodsSku(@RequestParam("sku_id")String sku_id, @RequestParam("price_input") BigDecimal price_input,@RequestParam("price_sale") BigDecimal price_sale,@RequestParam("stock") int stock, @RequestParam("spu_id")String spu_id, @RequestParam("specIds") String specIds) {

        RequsetData<Integer> res = new RequsetData<>();

        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuId(sku_id);
        goodsSku.setSpuId(spu_id);
        goodsSku.setPriceInput(price_input);
        goodsSku.setPriceSale(price_sale);
        goodsSku.setStock(stock);
        goodsSku.setGmtCreate(new Date());
        int count = goodsSkuService.insertSelective(goodsSku);

        JSONObject jsonObj = new JSONObject(specIds);
        for(String str:jsonObj.keySet()){
            if (!jsonObj.isNull(str)){
                count += goodsSkuService.insertGoodsSkuSpecValue(sku_id,jsonObj.get(str).toString(),new Date(),null);
            }
        }

        if (count >= 2) {
            res.setItem(count);
            res.setMsg("进货成功");
            res.setSuccess(true);
        } else {
            res.setMsg("进货失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/deleteGoodsSku")
    @ResponseBody
    public RequsetData<Integer> deleteGoodsSku(@RequestParam("sku_id")String sku_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = goodsSkuService.deleteByPrimaryKey(sku_id);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("删除成功");
            res.setSuccess(true);
        } else {
            res.setMsg("删除失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/updateGoodsSkuPriceSale")
    @ResponseBody
    public RequsetData<Integer> updateGoodsSkuPriceSale(@RequestParam("sku_id")String sku_id, @RequestParam("price_sale") BigDecimal price_sale) {

        RequsetData<Integer> res = new RequsetData<>();

        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuId(sku_id);
        goodsSku.setPriceSale(price_sale);
        goodsSku.setGmtUpdate(new Date());
        int count = goodsSkuService.updateByPrimaryKeySelective(goodsSku);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("修改成功");
            res.setSuccess(true);
        } else {
            res.setMsg("修改失败");
            res.setSuccess(false);
        }

        return res;
    }

    private RequsetData<List<HashMap<String, Object>>> setRequsetData(List<HashMap<String, Object>> list,String msg) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();
        if (!list.isEmpty()) {
            res.setItem(list);
            res.setMsg(msg + "成功");
            res.setSuccess(true);
        } else {
            res.setMsg(msg + "失败");
            res.setSuccess(false);
        }
        return res;
    }

}
