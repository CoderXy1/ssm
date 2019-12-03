package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsSpec;
import com.clothSale.service.IGoodsSpecService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/GoodsSpec")
public class GoodsSpecController {

    @Resource
    private IGoodsSpecService goodsSpecService;

    @RequestMapping("/selectGoodsSpecAndValue")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSpecAndValue(@RequestParam("spu_id") String spu_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsSpecService.selectGoodsSpecAndValue(spu_id);

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

    @RequestMapping("/selectGoodsSpecValue")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectGoodsSpecValue(@RequestParam("spec_id") String spec_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsSpecService.selectGoodsSpecValue(spec_id);

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

    @RequestMapping("/selectAllGoodsSpec")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectAllGoodsSpec(@RequestParam("category_id")String category_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsSpecService.selectAllGoodsSpec(category_id);

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

    @RequestMapping("/insertGoodsSpecSpu")
    @ResponseBody
    public RequsetData<Integer> insertGoodsSpecSpu(@RequestParam("spu_id")String spu_id,@RequestParam("spec_id")String spec_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = goodsSpecService.insertGoodsSpecSpu(spu_id,spec_id,new Date(),null);

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

    @RequestMapping("/insertGoodsSpec")
    @ResponseBody
    public RequsetData<Integer> insertGoodsSpec(@RequestParam("spec_id")String spec_id,@RequestParam("spec_name")String spec_name,@RequestParam("category_id")String category_id) {

        RequsetData<Integer> res = new RequsetData<>();
        int count = 0;

        String spec_id_search = goodsSpecService.selectBySpecName(spec_name);
        if (spec_id_search != null && spec_id_search != ""){
            count = goodsSpecService.insertGoodsSpecCategory(spec_id_search,category_id,new Date(),null);
        }else {
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setSpecId(spec_id);
            goodsSpec.setSpecName(spec_name);
            goodsSpec.setGmtCreate(new Date());
            count = goodsSpecService.insertSelective(goodsSpec);

            goodsSpecService.insertGoodsSpecCategory(spec_id,category_id,new Date(),null);
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

    @RequestMapping("/insertGoodsSpecValue")
    @ResponseBody
    public RequsetData<Integer> insertGoodsSpecValue(@RequestParam("spec_value_id")String spec_value_id,@RequestParam("spec_id")String spec_id,@RequestParam("spec_value")String spec_value) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = goodsSpecService.insertGoodsSpecValue(spec_value_id, spec_id, spec_value, new Date(),null);

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
