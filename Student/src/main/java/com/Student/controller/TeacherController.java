package com.Student.controller;

import com.Student.model.Admin;
import com.Student.model.Teacher;
import com.Student.model.TeacherDep;
import com.Student.service.IAdminService;
import com.Student.service.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @RequestMapping("/selectTeaByLogin")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public TeacherDep selectTeaByLogin(@RequestParam("id")String id, @RequestParam("password")String password){
        return this.teacherService.selectTeaByLogin(id, password);
    }

    @RequestMapping("/selectAllTeacher")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public List<Teacher> selectAllTeacher(@RequestParam("depId")int depId){
        return this.teacherService.selectAllTeacher(depId);
    }

}
