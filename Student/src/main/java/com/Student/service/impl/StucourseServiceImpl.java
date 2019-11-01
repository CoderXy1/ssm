package com.Student.service.impl;

import com.Student.mapper.StucourseMapper;
import com.Student.model.StuCourseDepTeaMajor;
import com.Student.model.StuMajorDep;
import com.Student.model.Stucourse;
import com.Student.model.Student;
import com.Student.service.IStucourseService;
import com.Student.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StucourseServiceImpl implements IStucourseService {

    @Resource
    private StucourseMapper stucourseMapper;

    @Override
    public int deleteByPrimaryKey(Integer scid) {
        return this.stucourseMapper.deleteByPrimaryKey(scid);
    }

    @Override
    public int insert(Stucourse record) {
        return this.stucourseMapper.insert(record);
    }

    @Override
    public int insertSelective(Stucourse record) {
        return this.stucourseMapper.insertSelective(record);
    }

    @Override
    public Stucourse selectByPrimaryKey(Integer scid) {
        return this.stucourseMapper.selectByPrimaryKey(scid);
    }

    @Override
    public int updateByPrimaryKeySelective(Stucourse record) {
        return this.stucourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Stucourse record) {
        return this.stucourseMapper.updateByPrimaryKey(record);
    }

    @Override
    public StuCourseDepTeaMajor selectStucourseByWeekAndPitch(int week, int pitchNum,String stuId,String term) {
        return this.stucourseMapper.selectStucourseByWeekAndPitch(week, pitchNum,stuId,term);
    }

    @Override
    public int selectStucourseNumByWeek(int week, String stuId, String term) {
        return this.stucourseMapper.selectStucourseNumByWeek(week, stuId, term);
    }

    @Override
    public int selectHasChooseClass(String stuId, String courseId, String term) {
        return this.stucourseMapper.selectHasChooseClass(stuId, courseId, term);
    }

    @Override
    public int deleteStuCorse(String stuId, String courseId, String term) {
        return this.stucourseMapper.deleteStuCorse(stuId, courseId, term);
    }

    @Override
    public int selectMaxStucourseId() {
        return this.stucourseMapper.selectMaxStucourseId();
    }

    @Override
    public int insertStuCourse(int scId, String stuId, String courseId, int week, String classRoom, int pitchNum, int totalWeeks, int score, Date scDate, String isPass, String isRebulid, String term) {
        return this.stucourseMapper.insertStuCourse(scId, stuId, courseId, week, classRoom, pitchNum, totalWeeks, score, scDate, isPass, isRebulid, term);
    }
}
