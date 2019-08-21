package com.resource.service;

import com.resource.model.Pic;

import java.util.Date;
import java.util.List;

public interface IPicService {

    List<Pic> selectAllPic();

    List<Pic> selectPicById(int picId);

    List<Pic> selectPicByType(String picType);

    List<Pic> selectPicByName(String picName,int pageIndex,int pageSize);

    int selectMaxPicId();

    int insertPic(int picId, String picName, String picPath, String picSize, String picDescribe, String picType, Date putDate);

    int deletePic(int picId);

    int updatePic(int picId,String picName,String picPath,String picSize,String picDescribe,String picType, Date putDate);

    int selectPicNum(String picName);

}
