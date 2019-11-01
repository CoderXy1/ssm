package com.Student.controller;

import com.Student.model.StuMajorDep;
import com.Student.model.Student;
import com.Student.service.IStudentService;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @RequestMapping("/selectByIdAndPw")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public StuMajorDep selectByIdAndPw(@RequestParam("id")String id, @RequestParam("password")String password){
            return this.studentService.selectByIdAndPw(id, password);
    }

    @RequestMapping("/updateByStuId")
    @ResponseBody
    public int updateByStuId(@RequestParam("stuid")String stuid, @RequestParam(required = false)String stuname, @RequestParam(required = false)String stupassword, @RequestParam(required = false)String sex, @RequestParam(required = false) String birthday,
                             @RequestParam(required = false)String birthplace, @RequestParam(required = false)String origo, @RequestParam(required = false)String idnumber, @RequestParam(required = false)String nation, @RequestParam(required = false)String nationality, @RequestParam(required = false)String phonenumber, @RequestParam(required = false)String address, @RequestParam(required = false)String grade, @RequestParam(required = false)String classname,
                             @RequestParam(required = false)int majorid, @RequestParam(required = false)String power, @RequestParam(required = false)String picpath) throws Exception{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday1 = sf.parse(birthday);
        stuname = URLDecoder.decode(stuname.toString(), "UTF-8");
        sex = URLDecoder.decode(sex.toString(), "UTF-8");
        birthplace = URLDecoder.decode(birthplace.toString(), "UTF-8");
        origo = URLDecoder.decode(origo.toString(), "UTF-8");
        nation = URLDecoder.decode(nation.toString(), "UTF-8");
        nationality = URLDecoder.decode(nationality.toString(), "UTF-8");
        address = URLDecoder.decode(address.toString(), "UTF-8");
        grade = URLDecoder.decode(grade.toString(), "UTF-8");
        classname = URLDecoder.decode(classname.toString(), "UTF-8");
        return this.studentService.updateByStuId(stuid, stuname, stupassword, sex,birthday1, birthplace, origo, idnumber, nation, nationality, phonenumber, address, grade, classname, majorid, power, picpath);
    }

    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public Student selectByPrimaryKey(@RequestParam("stuid")String stuid){
        return this.studentService.selectByPrimaryKey(stuid);
    }

    @RequestMapping("/selectStuByPage")
    @ResponseBody
    public List<StuMajorDep> selectStuByPage(@RequestParam(required = false)String stuId,@RequestParam(required = false)String stuName, @RequestParam(required = false)String sex, @RequestParam(required = false)String origo, @RequestParam(required = false)int depId,
                                             @RequestParam(required = false)String majorName, @RequestParam(required = false)String grade,@RequestParam(required = false)String className, @RequestParam("pageIndex")int pageIndex, @RequestParam("pageSize")int pageSize){
        return this.studentService.selectStuByPage(stuId,stuName, sex, origo, depId, majorName, grade,className, pageIndex, pageSize);
    }

    @RequestMapping("/selectStuNumBySearch")
    @ResponseBody
    public int selectStuNumBySearch(@RequestParam(required = false)String stuId,@RequestParam(required = false)String stuName, @RequestParam(required = false)String sex, @RequestParam(required = false)String origo, @RequestParam(required = false)int depId,
                                             @RequestParam(required = false)String majorName, @RequestParam(required = false)String grade,@RequestParam(required = false)String className){
        return this.studentService.selectStuNumBySearch(stuId,stuName, sex, origo, depId, majorName, grade,className);
    }

    @RequestMapping("/selectStuById")
    @ResponseBody
    public StuMajorDep selectStuById(@RequestParam("stuId")String stuId){
        return this.studentService.selectStuById(stuId);
    }

    @RequestMapping("/insertSelective")
    @ResponseBody
    public int insertSelective(@RequestParam("stuid")String stuid, @RequestParam(required = false)String stuname, @RequestParam(required = false)String stupassword, @RequestParam(required = false)String sex, @RequestParam(required = false) String birthday,
                                           @RequestParam(required = false)String birthplace, @RequestParam(required = false)String origo, @RequestParam(required = false)String idnumber, @RequestParam(required = false)String nation, @RequestParam(required = false)String nationality, @RequestParam(required = false)String phonenumber, @RequestParam(required = false)String address, @RequestParam(required = false)String grade, @RequestParam(required = false)String classname,
                                           @RequestParam(required = false)int majorid, @RequestParam(required = false)String power, @RequestParam(required = false)String picpath) throws Exception {
        Student student = new Student();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        student.setBirthday(sf.parse(birthday));
        student.setStuid(stuid);
        student.setStupassword(stupassword);
        student.setIdnumber(idnumber);
        student.setPhonenumber(phonenumber);
        student.setMajorid(majorid);
        student.setPower(majorid == 1?"1":"0");
        student.setPicpath("#");
        student.setStuname(URLDecoder.decode(stuname.toString(), "UTF-8"));
        student.setSex(URLDecoder.decode(sex.toString(), "UTF-8"));
        student.setBirthplace(URLDecoder.decode(birthplace.toString(), "UTF-8"));
        student.setOrigo(URLDecoder.decode(origo.toString(), "UTF-8"));
        student.setNation(URLDecoder.decode(nation.toString(), "UTF-8"));
        student.setNationality(URLDecoder.decode(nationality.toString(), "UTF-8"));
        student.setAddress(URLDecoder.decode(address.toString(), "UTF-8"));
        student.setGrade(URLDecoder.decode(grade.toString(), "UTF-8"));
        student.setClassname(URLDecoder.decode(classname.toString(), "UTF-8"));
        return this.studentService.insertSelective(student);
    }

    @RequestMapping("/selectStuNumByMajorClass")
    @ResponseBody
    public int selectStuNumByMajorClass(@RequestParam("majorid")int majorid,@RequestParam("grade")String grade,@RequestParam("classname")String classname){
        return this.studentService.selectStuNumByMajorClass(majorid,grade, classname);
    }

    @RequestMapping("/deleteByPrimaryKey")
    @ResponseBody
    public int deleteByPrimaryKey(@RequestParam("stuid")String stuid){
        return this.studentService.deleteByPrimaryKey(stuid);
    }


}
