package com.Student.controller;

import com.Student.model.Department;
import com.Student.model.Major;
import com.Student.service.IDepartmentService;
import com.Student.service.IMajorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Resource
    private IMajorService majorService;

    @RequestMapping("/selectMajorByDepId")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public List<Major> selectMajorByDepId(@RequestParam("depId")int depId) {
        return this.majorService.selectMajorByDepId(depId);
    }


}
