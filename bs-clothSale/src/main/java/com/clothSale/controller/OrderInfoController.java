package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.GoodsSku;
import com.clothSale.model.OrderCart;
import com.clothSale.model.OrderInfo;
import com.clothSale.service.IGoodsSkuService;
import com.clothSale.service.IOrderCartService;
import com.clothSale.service.IOrderInfoService;
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
@RequestMapping("/OrderInfo")
public class OrderInfoController {

    @Resource
    private IOrderInfoService orderInfoService;

    @Resource
    private IGoodsSkuService goodsSkuService;

    @RequestMapping("/selectAllOrderInfoByUserId")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectAllOrderInfoByUserId(@RequestParam(required = false) String user_id,@RequestParam(required = false)String user_name,@RequestParam("order_state") int order_state,@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = orderInfoService.selectAllOrderInfoByUserId(user_id,user_name, order_state, pageIndex, pageSize);
        HashMap<String,Object> total = orderInfoService.selectAllOrderInfoNumByUserId(user_id,user_name, order_state);

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

    @RequestMapping("/insertOrderInfo")
    @ResponseBody
    public RequsetData<Integer> insertOrderInfo(@RequestParam("order_info_id") String order_info_id,@RequestParam("user_id") String user_id,
                                                 @RequestParam("sku_id") String sku_id,@RequestParam("order_address")String order_address,
                                                @RequestParam("liaison_person")String liaison_person,@RequestParam("phone_number")String phone_number,@RequestParam("total_num")int total_num,@RequestParam("total_price") BigDecimal total_price) {

        RequsetData<Integer> res = new RequsetData<>();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderInfoId(order_info_id);
        orderInfo.setUserId(user_id);
        orderInfo.setSkuId(sku_id);
        orderInfo.setLiaisonPerson(liaison_person);
        orderInfo.setPhoneNumber(phone_number);
        orderInfo.setOrderAddress(order_address);
        orderInfo.setTotalNum(total_num);
        orderInfo.setTotalPrice(total_price);
        orderInfo.setOrderState(1); //1 待付款
        orderInfo.setGmtCreate(new Date());

        int count = orderInfoService.insertSelective(orderInfo);

        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuId(sku_id);
        goodsSku.setStock(0-total_num);
        count += goodsSkuService.updateByPrimaryKeySelective(goodsSku);

        if (count == 2) {
            res.setItem(count);
            res.setMsg("加入订单成功,请尽快付款");
            res.setSuccess(true);
        } else {
            res.setMsg("加入订单失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/updateOrderInfo")
    @ResponseBody
    public RequsetData<Integer> updateOrderInfo(@RequestParam("order_info_id") String order_info_id,@RequestParam(required = false)String order_address,
                                                @RequestParam(required = false)String liaison_person,@RequestParam(required = false)String phone_number,
                                                @RequestParam(required = false)String pay_way,@RequestParam(required = false)int order_state) {

        RequsetData<Integer> res = new RequsetData<>();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderInfoId(order_info_id);
        orderInfo.setPayWay(pay_way);
        orderInfo.setOrderAddress(order_address);
        orderInfo.setLiaisonPerson(liaison_person);
        orderInfo.setPhoneNumber(phone_number);
        orderInfo.setPayDatetime(new Date());
        orderInfo.setOrderState(order_state);

        int count = orderInfoService.updateByPrimaryKeySelective(orderInfo);

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

    @RequestMapping("/deleteOrderInfo")
    @ResponseBody
    public RequsetData<Integer> deleteOrderInfo(@RequestParam("order_info_id") String order_info_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = orderInfoService.deleteByPrimaryKey(order_info_id);

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
