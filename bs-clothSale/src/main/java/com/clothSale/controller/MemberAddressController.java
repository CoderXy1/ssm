package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.MemberAddress;
import com.clothSale.service.IAddressAreasService;
import com.clothSale.service.IMemberAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/memberAddress")
public class MemberAddressController {

    @Resource
    private IMemberAddressService memberAddressService;

    @RequestMapping("/selectAllMemberAddress")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectAllMemberAddress(@RequestParam(required = false) String user_id, @RequestParam(required = false) String user_name, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = memberAddressService.selectAllMemberAddress(user_id, user_name, pageIndex, pageSize);
        HashMap<String, Object> total = memberAddressService.selectAllMemberAddressNum(user_id, user_name);

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

    @RequestMapping("/deleteMemberAddress")
    @ResponseBody
    public RequsetData<Integer> deleteMemberAddress(@RequestParam("address_id") String address_id) {

        RequsetData<Integer> res = new RequsetData<>();

        int count = memberAddressService.deleteByPrimaryKey(address_id);

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

    @RequestMapping("/insertMemberAddress")
    @ResponseBody
    public RequsetData<Integer> insertMemberAddress(@RequestParam("address_id") String address_id,@RequestParam("area_id")int area_id,@RequestParam("details_address")String details_address,@RequestParam("liaison_person")String liaison_person,@RequestParam("phone_number")String phone_number,@RequestParam("postal_code")String postal_code,@RequestParam("user_id")String user_id) {

        RequsetData<Integer> res = new RequsetData<>();

        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setAddressId(address_id);
        memberAddress.setAreaId(area_id);
        memberAddress.setDetailsAddress(details_address);
        memberAddress.setLiaisonPerson(liaison_person);
        memberAddress.setPhoneNumber(phone_number);
        memberAddress.setPostalCode(postal_code);

        int count = memberAddressService.insertSelective(memberAddress);
        count += memberAddressService.insertMemberUserAddress(address_id,user_id,new Date());

        if (count == 2) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/selectSingleMemberAddress")
    @ResponseBody
    public RequsetData<HashMap<String, Object>> selectSingleMemberAddress(@RequestParam("address_id") String address_id) {

        RequsetData<HashMap<String, Object>> res = new RequsetData<>();

        HashMap<String, Object> list = memberAddressService.selectSingleMemberAddress(address_id);

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
