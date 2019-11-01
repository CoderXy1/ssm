package com.Student.service;

import com.Student.model.StuMajorDep;
import com.Student.model.Student;

import java.util.Date;
import java.util.List;

public interface IStudentService {

    int deleteByPrimaryKey(String stuid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String stuid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    StuMajorDep selectByIdAndPw(String id, String password);

    int updateByStuId(String stuid, String stuname, String stupassword, String sex, Date birthday,
                      String birthplace,String origo,String idnumber,String nation,String nationality,String phonenumber,String address,String grade,String classname,
                      int majorId,String power,String picpath);

    List<StuMajorDep> selectStuByPage(String stuId,String stuName, String sex,String origo,int depId,String majorName,String grade,String className,int pageIndex,int pageSize);

    StuMajorDep selectStuById(String stuId);

    int selectStuNumByMajorClass(int majorid,String grade,String classname);

    int selectStuNumBySearch(String stuId,String stuName, String sex,String origo,int depId,String majorName,String grade,String className);
}
