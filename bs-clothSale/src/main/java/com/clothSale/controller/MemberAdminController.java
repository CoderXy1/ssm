package com.clothSale.controller;


import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.MemberAdmin;
import com.clothSale.service.IAddressAreasService;
import com.clothSale.service.IMemberAdminService;
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
@RequestMapping("/memberAdmin")
public class MemberAdminController {

    @Resource
    private IMemberAdminService memberAdminService;

    @RequestMapping("/selectAdminByLogin")
    @ResponseBody
    public RequsetData<List<HashMap<String,Object>>> selectAdminByLogin(String admin_name,String admin_password) {
        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String,Object>> list = memberAdminService.selectAdminByLogin(admin_name,DigestUtils.md5DigestAsHex(admin_password.getBytes()).toUpperCase());

        if (!list.isEmpty()) {
            MemberAdmin memberAdmin = new MemberAdmin();
            memberAdmin.setAdminId(list.get(0).get("admin_id").toString());
            memberAdmin.setLoginTimes(0); //修改登录次数 不为空自加1
            memberAdmin.setLoginDatetime(new Date()); //修改登录次数
            memberAdminService.updateByPrimaryKeySelective(memberAdmin);
            res.setItem(list);
            res.setMsg("登录成功");
            res.setSuccess(true);
        } else {
            res.setMsg("登陆失败，账号或密码错误");
            res.setSuccess(false);
        }
        return res;
    }

    private RequsetData<List<HashMap<String, Object>>> setRequsetData(List<HashMap<String, Object>> list,String msgSuccess,String msgError) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();
        if (!list.isEmpty()) {
            res.setItem(list);
            res.setMsg(msgSuccess);
            res.setSuccess(true);
        } else {
            res.setMsg(msgError);
            res.setSuccess(false);
        }
        return res;
    }


}
