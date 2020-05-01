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
    public RequsetData<List<HashMap<String,Object>>> selectAdminByLogin(@RequestParam("admin_name") String admin_name,@RequestParam("admin_password")String admin_password) {
        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String,Object>> list = memberAdminService.selectAdminByLogin(admin_name,DigestUtils.md5DigestAsHex(admin_password.getBytes()).toUpperCase());

        if (!list.isEmpty()) {
            MemberAdmin memberAdmin = new MemberAdmin();
            memberAdmin.setAdminId(list.get(0).get("admin_id").toString());
            memberAdmin.setLoginTimes(0); //修改登录次数 不为空自加1
            memberAdmin.setLoginDatetime(new Date()); //修改登录时间
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

    @RequestMapping("/selectAllAdmin")
    @ResponseBody
    public RequsetData<List<HashMap<String,Object>>> selectAllAdmin(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,@RequestParam(required = false) String admin_name) {

        List<HashMap<String,Object>> list = memberAdminService.selectAllAdmin(pageIndex, pageSize, admin_name);
        HashMap<String,Object> total = memberAdminService.selectAllAdminNum(admin_name);

        return setRequsetData(list,"成功","失败",total);
    }

    @RequestMapping("/insertMemberAdmin")
    @ResponseBody
    public RequsetData<Integer> insertMemberAdmin(@RequestParam("admin_id") String admin_id,@RequestParam("admin_name") String admin_name,@RequestParam("real_name") String real_name,
                                                  @RequestParam("admin_password")String admin_password,@RequestParam("admin_phone") String admin_phone,@RequestParam("admin_icon_id") String admin_icon_id) {
        RequsetData<Integer> res = new RequsetData<>();

        MemberAdmin memberAdmin = new MemberAdmin();
        memberAdmin.setAdminId(admin_id);
        memberAdmin.setAdminName(admin_name);
        memberAdmin.setAdminPassword(DigestUtils.md5DigestAsHex(admin_password.getBytes()).toUpperCase());
        memberAdmin.setRealName(real_name);
        memberAdmin.setAdminPhone(admin_phone);
        memberAdmin.setAdminIconId(admin_icon_id);
        memberAdmin.setGmtCreate(new Date());
        memberAdmin.setLoginTimes(0);
        int count =  memberAdminService.insertSelective(memberAdmin);
        if (count == 1) {
            res.setItem(count);
            res.setMsg("注册成功");
            res.setSuccess(true);
        } else {
            res.setMsg("注册失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/updateMemberAdmin")
    @ResponseBody
    public RequsetData<Integer> updateMemberAdmin(@RequestParam("admin_id") String admin_id,@RequestParam("admin_password")String admin_password) {
        RequsetData<Integer> res = new RequsetData<>();

        MemberAdmin memberAdmin = new MemberAdmin();
        memberAdmin.setAdminId(admin_id);
        memberAdmin.setAdminPassword(DigestUtils.md5DigestAsHex(admin_password.getBytes()).toUpperCase());
        int count =  memberAdminService.updateByPrimaryKeySelective(memberAdmin);
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
