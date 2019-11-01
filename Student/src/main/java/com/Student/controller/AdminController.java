package com.Student.controller;

import com.Student.model.Admin;
import com.Student.model.StuMajorDep;
import com.Student.model.Student;
import com.Student.service.IAdminService;
import com.Student.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @RequestMapping("/selectAdminByLogin")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public Admin selectAdminByLogin(@RequestParam("id")String id, @RequestParam("password")String password){
        return this.adminService.selectAdminByLogin(id, password);
    }



}
