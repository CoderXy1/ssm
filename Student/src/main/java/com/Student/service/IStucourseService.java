package com.Student.service;

import com.Student.model.StuCourseDepTeaMajor;
import com.Student.model.Stucourse;

import java.util.Date;

public interface IStucourseService {

    int deleteByPrimaryKey(Integer scid);

    int insert(Stucourse record);

    int insertSelective(Stucourse record);

    Stucourse selectByPrimaryKey(Integer scid);

    int updateByPrimaryKeySelective(Stucourse record);

    int updateByPrimaryKey(Stucourse record);

    StuCourseDepTeaMajor selectStucourseByWeekAndPitch(int week, int pitchNum,String stuId,String term);

    int selectStucourseNumByWeek(int week,String stuId,String term);

    int selectHasChooseClass(String stuId,String courseId,String term);

    int deleteStuCorse(String stuId,String courseId,String term);

    int selectMaxStucourseId();

    int insertStuCourse(int scId, String stuId, String courseId, int week, String classRoom,
                        int pitchNum, int totalWeeks, int score, Date scDate,String isPass,String isRebulid,String term);

}
