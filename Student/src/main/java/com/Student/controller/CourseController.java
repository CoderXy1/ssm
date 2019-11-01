package com.Student.controller;

import com.Student.model.Admin;
import com.Student.model.CourseTeaDep;
import com.Student.service.IAdminService;
import com.Student.service.ICourseService;
import com.Student.service.IStucourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;
    @Resource
    private IStucourseService stucourseService;

    @RequestMapping("/selectCourse")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public List<HashMap<String,Object>> selectCourse(@RequestParam("stuId")String stuId,@RequestParam(required = false)String courseName, @RequestParam(required = false)String teaId,
                                                     @RequestParam(required = false)int depId, @RequestParam("pageIndex")int pageIndex, @RequestParam("pageSize")int pageSize){
        List<HashMap<String,Object>> list = this.courseService.selectCourse(courseName, teaId, depId,pageIndex,pageSize);
        for (HashMap<String,Object> map : list){
            map.put("isSelect",this.stucourseService.selectHasChooseClass(stuId,map.get("courseId").toString(),"2019上学期"));
        }
        return list;
    }

    @RequestMapping("/selectCourseNum")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public int selectCourseNum(@RequestParam(required = false)String courseName, @RequestParam(required = false)String teaId,
                                           @RequestParam(required = false)int depId){
        return this.courseService.selectCourseNum(courseName, teaId, depId);
    }

}
