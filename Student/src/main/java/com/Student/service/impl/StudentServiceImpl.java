package com.Student.service.impl;

import com.Student.mapper.StudentMapper;
import com.Student.model.StuMajorDep;
import com.Student.model.Student;
import com.Student.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(String stuid) {
        return this.studentMapper.deleteByPrimaryKey(stuid);
    }

    @Override
    public int insert(Student record) {
        return this.studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return this.studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(String stuid) {
        return this.studentMapper.selectByPrimaryKey(stuid);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return this.studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return this.studentMapper.updateByPrimaryKey(record);
    }

    @Override
    public StuMajorDep selectByIdAndPw(String id, String password) {
        return this.studentMapper.selectByIdAndPw(id,password);
    }

    @Override
    public int updateByStuId(String stuid, String stuname, String stupassword, String sex, Date birthday,
                             String birthplace,String origo,String idnumber,String nation,String nationality,String phonenumber,String address,String grade,String classname,
                             int majorId,String power,String picpath) {
        return this.studentMapper.updateByStuId(stuid, stuname, stupassword, sex, birthday, birthplace, origo, idnumber, nation, nationality, phonenumber, address, grade, classname, majorId, power, picpath);
    }

    @Override
    public List<StuMajorDep> selectStuByPage(String stuId,String stuName, String sex, String origo, int depId, String majorName,String grade, String className, int pageIndex, int pageSize) {
        return this.studentMapper.selectStuByPage(stuId,stuName, sex, origo, depId,majorName,grade,className, pageIndex, pageSize);
    }

    @Override
    public StuMajorDep selectStuById(String stuId) {
        return this.studentMapper.selectStuById(stuId);
    }

    @Override
    public int selectStuNumByMajorClass(int majorid,String grade,String classname) {
        return this.studentMapper.selectStuNumByMajorClass(majorid,grade,classname);
    }

    @Override
    public int selectStuNumBySearch(String stuId, String stuName, String sex, String origo, int depId, String majorName, String grade, String className) {
        return this.studentMapper.selectStuNumBySearch(stuId, stuName, sex, origo, depId, majorName, grade, className);
    }
}
