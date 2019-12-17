package com.clothSale.controller;


import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.MemberAdmin;
import com.clothSale.model.MemberUserinfo;
import com.clothSale.service.IMemberAdminService;
import com.clothSale.service.IMemberUserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/memberUserinfo")
public class MemberUserinfoController {

    @Resource
    private IMemberUserinfoService memberUserinfoService;

    @RequestMapping("/selectUserinfoByLogin")
    @ResponseBody
    public RequsetData<List<HashMap<String,Object>>> selectUserinfoByLogin(@RequestParam("user_name") String user_name,@RequestParam("user_password")String user_password) {
        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String,Object>> list = memberUserinfoService.selectUserinfoByLogin(user_name,DigestUtils.md5DigestAsHex(user_password.getBytes()).toUpperCase());

        if (!list.isEmpty()) {
            MemberUserinfo memberUserinfo = new MemberUserinfo();
            memberUserinfo.setUserId(list.get(0).get("user_id").toString());
            memberUserinfo.setLoginTimes(0); //修改登录次数 不为空自加1
            memberUserinfo.setLoginDatetime(new Date()); //修改登录时间
            memberUserinfoService.updateByPrimaryKeySelective(memberUserinfo);
            res.setItem(list);
            res.setMsg("登录成功");
            res.setSuccess(true);
        } else {
            res.setMsg("登陆失败，账号或密码错误");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/selectAllUserinfo")
    @ResponseBody
    public RequsetData<List<HashMap<String,Object>>> selectAllUserinfo(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,@RequestParam(required = false) String user_name) {

        List<HashMap<String,Object>> list = memberUserinfoService.selectAllUserinfo(pageIndex, pageSize, user_name);
        HashMap<String,Object> total = memberUserinfoService.selectAllUserinfoNum(user_name);

        return setRequsetData(list,"成功","失败",total);
    }

    @RequestMapping("/insertMemberUserinfo")
    @ResponseBody
    public RequsetData<Integer> insertMemberUserinfo(@RequestParam("user_id") String user_id,@RequestParam("user_name") String user_name,@RequestParam("phone_number") String phone_number,
                                                  @RequestParam("user_password")String user_password,@RequestParam("email") String email,@RequestParam("icon_id") String icon_id) {
        RequsetData<Integer> res = new RequsetData<>();

        MemberUserinfo memberUserinfo = new MemberUserinfo();
        memberUserinfo.setUserId(user_id);
        memberUserinfo.setUserName(user_name);
        memberUserinfo.setPhoneNumber(phone_number);
        memberUserinfo.setUserPassword(user_password);
        memberUserinfo.setEmail(email);
        memberUserinfo.setGmtCreate(new Date());
        memberUserinfo.setIconId(icon_id);
        memberUserinfo.setLoginTimes(0);
        int count =  memberUserinfoService.insertSelective(memberUserinfo);
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

    @RequestMapping("/updateMemberUserinfo")
    @ResponseBody
    public RequsetData<Integer> updateMemberUserinfo(@RequestParam("user_id") String user_id,@RequestParam(required = false)String user_password) {
        RequsetData<Integer> res = new RequsetData<>();

        MemberUserinfo memberUserinfo = new MemberUserinfo();
        memberUserinfo.setUserId(user_id);
        memberUserinfo.setUserPassword(user_password);
        int count =  memberUserinfoService.updateByPrimaryKeySelective(memberUserinfo);
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

    private RequsetData<List<HashMap<String, Object>>> setRequsetData(List<HashMap<String, Object>> list,String msgSuccess,String msgError,HashMap<String,Object> extdata) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();
        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(extdata);
            res.setMsg(msgSuccess);
            res.setSuccess(true);
        } else {
            res.setMsg(msgError);
            res.setSuccess(false);
        }
        return res;
    }


}
