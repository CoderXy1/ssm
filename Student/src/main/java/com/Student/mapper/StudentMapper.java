package com.Student.mapper;

import com.Student.model.StuMajorDep;
import com.Student.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(@Param("stuid") String stuid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(@Param("stuid")String stuid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    StuMajorDep selectByIdAndPw(@Param("id")String id, @Param("password")String password);

    int updateByStuId(@Param("stuid")String stuid, @Param("stuname")String stuname, @Param("stupassword")String stupassword, @Param("sex")String sex, @Param("birthday")Date birthday,
                      @Param("birthplace")String birthplace,@Param("origo")String origo,@Param("idnumber")String idnumber,@Param("nation")String nation,@Param("nationality")String nationality,@Param("phonenumber")String phonenumber,@Param("address")String address,@Param("grade")String grade,@Param("classname")String classname,
                      @Param("majorid")int majorid,@Param("power")String power,@Param("picpath")String picpath);

    List<StuMajorDep> selectStuByPage(@Param("stuId")String stuId,@Param("stuName")String stuName, @Param("sex")String sex, @Param("origo")String origo, @Param("depId")int depId,
                                  @Param("majorName")String majorName,@Param("grade")String grade, @Param("className")String className, @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

    StuMajorDep selectStuById(@Param("stuId")String stuId);

    int selectStuNumByMajorClass(@Param("majorid")int majorid,@Param("grade")String grade,@Param("classname")String classname);

    int selectStuNumBySearch(@Param("stuId")String stuId,@Param("stuName")String stuName, @Param("sex")String sex, @Param("origo")String origo, @Param("depId")int depId,
                             @Param("majorName")String majorName,@Param("grade")String grade, @Param("className")String className);
}