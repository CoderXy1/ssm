package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsCategory;
import com.clothSale.service.IGoodsCategoryService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/GoodsCategory")
public class GoodsCategoryController {

    @Resource
    private IGoodsCategoryService goodsCategoryService;

    @RequestMapping("/selectGoodsCategory")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectGoodsCategory(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String categoryName, @RequestParam(required = false) String category_first_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsCategoryService.selectGoodsCategory(pageIndex, pageSize, categoryName, category_first_id);
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

    @RequestMapping("/selectGoodsCategoryFirst")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectGoodsCategoryFirst(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String categoryFirstName) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsCategoryService.selectGoodsCategoryFirst(pageIndex, pageSize, categoryFirstName);
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

    @RequestMapping("/insertGoodsCategory")
    @ResponseBody
    public RequsetData<Integer> insertGoodsCategory(@RequestParam("category_id") String category_id, @RequestParam("category_name") String category_name, @RequestParam("category_order") int category_order, @RequestParam("category_first_id") String category_first_id) {

        RequsetData<Integer> res = new RequsetData<>();

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setCategoryId(category_id);
        goodsCategory.setCategoryName(category_name);
        goodsCategory.setCategoryOrder(category_order);
        goodsCategory.setGmtCreate(new Date());

        int count = goodsCategoryService.insertSelective(goodsCategory);

        JSONObject jsonObj = new JSONObject(category_first_id);
        for (String str : jsonObj.keySet()) {
            if (jsonObj.get(str) != null) {
                count += goodsCategoryService.insertCategoryConnect(jsonObj.get(str).toString(), category_id, new Date(), null);
            }
        }

        if (count >= 2) {
            res.setItem(count);
            res.setMsg("添加成功");
            res.setSuccess(true);
        } else {
            res.setMsg("添加失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/insertCategoryFirst")
    @ResponseBody
    public RequsetData<Integer> insertCategoryFirst(@RequestParam("categoryFirstId") String categoryFirstId, @RequestParam("categoryFirstName") String categoryFirstName, @RequestParam("categoryFirstOrder") int categoryFirstOrder) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = goodsCategoryService.insertCategoryFirst(categoryFirstId, categoryFirstName, categoryFirstOrder, new Date(), null);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("添加成功");
            res.setSuccess(true);
        } else {
            res.setMsg("添加失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/updateAllCategory")
    @ResponseBody
    public RequsetData<Integer> updateAllCategory(@RequestParam("categoryId") String categoryId, @RequestParam("category_name_edit") String category_name_edit, @RequestParam("category_order_edit") int category_order_edit, @RequestParam("categoryStep") int categoryStep) {

        RequsetData<Integer> res = new RequsetData<>();
        int count = 0;

        if (categoryStep == 1) { //一级分类
            count = goodsCategoryService.updateGoodsCategoryFirst(categoryId,category_name_edit,category_order_edit,new Date());
        } else { //二级分类
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setCategoryId(categoryId);
            goodsCategory.setCategoryName(category_name_edit);
            goodsCategory.setCategoryOrder(category_order_edit);
            goodsCategory.setGmtUpdate(new Date());
            count = goodsCategoryService.updateByPrimaryKeySelective(goodsCategory);
        }

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

    @RequestMapping("/deleteGoodsCategory")
    @ResponseBody
    public RequsetData<Integer> deleteGoodsCategory(@RequestParam("category_id") String category_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = goodsCategoryService.deleteByPrimaryKey(category_id);

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

    @RequestMapping("/deleteAllGoodsCategory")
    @ResponseBody
    public RequsetData<Integer> deleteAllGoodsCategory(@RequestParam("category_id") String category_id,@RequestParam("categoryStep")int categoryStep) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = 0;

        if (categoryStep == 1) { //一级分类
            count = goodsCategoryService.deleteGoodsCategoryFirst(category_id);
        } else { //二级分类
            count = goodsCategoryService.deleteByPrimaryKey(category_id);
        }

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

    @RequestMapping("/selectCategorySpuTotal")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectCategorySpuTotal() {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = goodsCategoryService.selectCategorySpuTotal();

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
