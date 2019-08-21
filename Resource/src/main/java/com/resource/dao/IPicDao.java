package com.resource.dao;

import com.resource.model.Pic;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IPicDao {

    List<Pic> selectAllPic();

    List<Pic> selectPicById(@Param("picId") int picId);

    List<Pic> selectPicByType(@Param("picType")String picType);

    List<Pic> selectPicByName(@Param("picName")String picName,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);

    int selectMaxPicId();

    int insertPic(@Param("picId")int picId,@Param("picName")String picName,@Param("picPath")String picPath,@Param("picSize")String picSize,@Param("picDescribe")String picDescribe,@Param("picType")String picType, @Param("putDate")Date putDate);

    int deletePic(@Param("picId")int picId);

    int updatePic(@Param("picId")int picId,@Param("picName")String picName,@Param("picPath")String picPath,@Param("picSize")String picSize,@Param("picDescribe")String picDescribe,@Param("picType")String picType, @Param("putDate")Date putDate);

    int selectPicNum(@Param("picName")String picName);

}
