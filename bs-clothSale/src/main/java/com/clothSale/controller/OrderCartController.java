package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsSpu;
import com.clothSale.model.OrderCart;
import com.clothSale.service.IGoodsSpecService;
import com.clothSale.service.IGoodsSpuService;
import com.clothSale.service.IOrderCartService;
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
@RequestMapping("/OrderCart")
public class OrderCartController {

    @Resource
    private IOrderCartService orderCartService;

    @RequestMapping("/selectCartByUserId")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectCartByUserId(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,@RequestParam("user_id") String user_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = orderCartService.selectCartByUserId(pageIndex, pageSize, user_id);
        HashMap<String,Object> total = orderCartService.selectCartNumByUserId(user_id);

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

    @RequestMapping("/insertOrderCart")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<Integer> insertOrderCart(@RequestParam("cart_id") String cart_id,@RequestParam("user_id") String user_id,
                                                 @RequestParam("sku_id") String sku_id,@RequestParam("total_num")int total_num) {

        RequsetData<Integer> res = new RequsetData<>();

        OrderCart orderCart = new OrderCart();
        orderCart.setCartId(cart_id);
        orderCart.setUserId(user_id);
        orderCart.setSkuId(sku_id);
        orderCart.setTotalNum(total_num);
        orderCart.setCartState(1);
        orderCart.setGmtCreate(new Date());

        int count = orderCartService.insertSelective(orderCart);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("加入购物车成功");
            res.setSuccess(true);
        } else {
            res.setMsg("加入购物车失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/deleteOrderCart")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<Integer> deleteOrderCart(@RequestParam("cart_id") String cart_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = orderCartService.deleteByPrimaryKey(cart_id);

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


}
