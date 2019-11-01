package com.Student.controller;

import com.Student.model.Department;
import com.Student.model.StuMajorDep;
import com.Student.model.Student;
import com.Student.service.IDepartmentService;
import com.Student.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;

    @RequestMapping("/selectAllDep")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public List<Department> selectAllDep() {
        return this.departmentService.selectAllDep();
    }


}
