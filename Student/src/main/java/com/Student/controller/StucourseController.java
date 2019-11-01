package com.Student.controller;

import com.Student.model.Admin;
import com.Student.model.StuCourseDepTeaMajor;
import com.Student.service.IAdminService;
import com.Student.service.IStucourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/stucourse")
public class StucourseController {

    @Resource
    private IStucourseService stucourseService;

    @RequestMapping("/selectStucourseByWeekAndPitch")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public List<HashMap<Integer,StuCourseDepTeaMajor>> selectStucourseByWeekAndPitch(@RequestParam("stuId")String stuId,@RequestParam("term")String term){
        List<HashMap<Integer,StuCourseDepTeaMajor>> list = new ArrayList<>();
        for (int pitchNum= 1;pitchNum <= 11;pitchNum++){
            HashMap<Integer,StuCourseDepTeaMajor> map = new HashMap<>();
            for (int week = 1;week <= 7;week++){
                map.put(week,this.stucourseService.selectStucourseByWeekAndPitch(week,pitchNum,stuId,term));
            }
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/selectStucourseNumByWeek")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public int selectStucourseNumByWeek(@RequestParam("week")int week,@RequestParam("stuId")String stuId,@RequestParam("term")String term){
        return this.stucourseService.selectStucourseNumByWeek(week, stuId, term);
    }

    @RequestMapping("/selectHasChooseClass")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public int selectHasChooseClass(@RequestParam("stuId")String stuId, @RequestParam("courseId")String courseId, @RequestParam("term")String term){
        return this.stucourseService.selectHasChooseClass(stuId, courseId, term);
    }

    @RequestMapping("/deleteStuCorse")
    @ResponseBody
    public int deleteStuCorse(@RequestParam("stuId")String stuId, @RequestParam("courseId")String courseId, @RequestParam("term")String term){
        return this.stucourseService.deleteStuCorse(stuId, courseId, term);
    }

    @RequestMapping("/insertStuCourse")
    @ResponseBody
    public int insertStuCourse(@RequestParam("stuId")String stuId, @RequestParam("courseId")String courseId, @RequestParam("term")String term){
        int scId = this.stucourseService.selectMaxStucourseId() + 1;
        int week = 0;
        int pitchNum = 0;
        week:
        for (int i= 1;i <= 7;i++){
            if (this.stucourseService.selectStucourseNumByWeek(i,stuId,term) < 3){
                pitchNum:
                for (int j = 1;j <= 11;j++){
                    if (this.stucourseService.selectStucourseByWeekAndPitch(i,j,stuId,term) == null){
                        week = i;
                        pitchNum = j;
                        break week;
                    }else {
                        j = j + (int)(Math.random()*4);
                    }
                }
            }
        }
        return this.stucourseService.insertStuCourse(scId, stuId, courseId, week, "2-101", pitchNum,18, 0, new Date(), "0", "0", term);
    }


}
