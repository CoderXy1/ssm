package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsSpu;
import com.clothSale.service.IGoodsSpecService;
import com.clothSale.service.IGoodsSpuService;
import org.json.JSONObject;
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
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSpu(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String category_id, @RequestParam(required = false) String brand_id,@RequestParam(required = false)String goods_name) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsSpuService.selectGoodsSpu(pageIndex, pageSize, category_id, brand_id,goods_name);
        HashMap<String, Object> total = goodsSpuService.selectGoodsSpuNum(category_id, brand_id,goods_name);

        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(total);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    //随机取若干条数据
    @RequestMapping("/selectGoodsSpuRand")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSpuRand(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String category_id, @RequestParam(required = false) String brand_id,@RequestParam(required = false)String goods_name) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsSpuService.selectGoodsSpuRand(pageIndex, pageSize, category_id, brand_id,goods_name);
        HashMap<String, Object> total = goodsSpuService.selectGoodsSpuNum(category_id, brand_id,goods_name);

        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(total);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/selectSingleGoodsSpu")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectSingleGoodsSpu(@RequestParam("spu_id") String spu_id){
        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        HashMap<String, Object> spu = goodsSpuService.selectSingleGoodsSpu(spu_id);
        List<HashMap<String, Object>> spec_list = goodsSpecService.selectGoodsSpecAndValue(spu_id);

        if (!spu.isEmpty()) {
            res.setItem(spec_list);
            res.setExtdata(spu);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public RequsetData<GoodsSpu> selectByPrimaryKey(@RequestParam("spu_id") String spu_id){
        RequsetData<GoodsSpu> res = new RequsetData<>();

        GoodsSpu spu = goodsSpuService.selectByPrimaryKey(spu_id);

        if (spu != null) {
            res.setItem(spu);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/selectGoodsSpuNum")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSpuNum(@RequestParam(required = false) String category_id, @RequestParam(required = false) String brand_id,@RequestParam(required = false)String goods_name) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        HashMap<String, Object> total = goodsSpuService.selectGoodsSpuNum(category_id, brand_id,goods_name);

        if (!total.isEmpty()) {
            res.setExtdata(total);
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
                                                                      @RequestParam("category_id") String category_id,@RequestParam("brand_id")String brand_id,@RequestParam("specIds")String specIds,@RequestParam(required = false)int spu_order) {

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

        JSONObject jsonObj = new JSONObject(specIds);
        for(String str:jsonObj.keySet()){
            if (!jsonObj.isNull(str)){
                count += goodsSpecService.insertGoodsSpecSpu(spu_id,jsonObj.get(str).toString(),new Date(),null);
            }
        }

        if (count >= 2) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/deleteGoodsSpu")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<Integer> selectGoodsSpu(@RequestParam("spu_id") String spu_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = goodsSpuService.deleteByPrimaryKey(spu_id);

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

    @RequestMapping("/updateGoodsSpu")
    @ResponseBody
    public RequsetData<Integer> updateGoodsSpu(@RequestParam("spuId") String spuId,@RequestParam("goodsName") String goodsName,
                                               @RequestParam("lowPrice") BigDecimal lowPrice,@RequestParam("spuOrder")int spuOrder) {

        RequsetData<Integer> res = new RequsetData<>();

        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setSpuId(spuId);
        goodsSpu.setGoodsName(goodsName);
        goodsSpu.setLowPrice(lowPrice);
        goodsSpu.setSpuOrder(spuOrder);
        goodsSpu.setGmtUpdate(new Date());

        int count = goodsSpuService.updateByPrimaryKeySelective(goodsSpu);

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

}
