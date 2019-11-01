package com.Student.mapper;

import com.Student.model.StuCourseDepTeaMajor;
import com.Student.model.Stucourse;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface StucourseMapper {
    int deleteByPrimaryKey(Integer scid);

    int insert(Stucourse record);

    int insertSelective(Stucourse record);

    Stucourse selectByPrimaryKey(Integer scid);

    int updateByPrimaryKeySelective(Stucourse record);

    int updateByPrimaryKey(Stucourse record);

    StuCourseDepTeaMajor selectStucourseByWeekAndPitch(@Param("week") int week, @Param("pitchNum")int pitchNum,@Param("stuId")String stuId,@Param("term")String term);

    int selectStucourseNumByWeek(@Param("week")int week,@Param("stuId")String stuId,@Param("term")String term);

    int selectHasChooseClass(@Param("stuId")String stuId,@Param("courseId")String courseId,@Param("term")String term);

    int deleteStuCorse(@Param("stuId")String stuId,@Param("courseId")String courseId,@Param("term")String term);

    int selectMaxStucourseId();

    int insertStuCourse(@Param("scId")int scId, @Param("stuId")String stuId, @Param("courseId")String courseId, @Param("week")int week, @Param("classRoom")String classRoom,
                        @Param("pitchNum")int pitchNum, @Param("totalWeeks")int totalWeeks, @Param("score")int score, @Param("scDate")Date scDate, @Param("isPass")String isPass, @Param("isRebulid")String isRebulid, @Param("term")String term);
}