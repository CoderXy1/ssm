package com.resource.service.impl;

import com.resource.dao.IPicDao;
import com.resource.model.Pic;
import com.resource.service.IPicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("picService")
public class PicServiceImpl implements IPicService {

    @Resource
    private IPicDao picDao;

    @Override
    public List<Pic> selectAllPic() {
        return this.picDao.selectAllPic();
    }

    @Override
    public List<Pic> selectPicById(int picId) {
        return this.picDao.selectPicById(picId);
    }

    @Override
    public List<Pic> selectPicByName(String picName,int pageIndex,int pageSize) {
        return this.picDao.selectPicByName(picName, pageIndex, pageSize);
    }

    @Override
    public int selectMaxPicId() {
        return this.picDao.selectMaxPicId();
    }

    @Override
    public int insertPic(int picId, String picName, String picPath, String picSize, String picDescribe,String picType, Date putDate) {
        return this.picDao.insertPic(picId,picName, picPath, picSize, picDescribe,picType,putDate);
    }

    @Override
    public int deletePic(int picId) {
        return this.picDao.deletePic(picId);
    }

    @Override
    public int updatePic(int picId, String picName, String picPath, String picSize, String picDescribe,String picType, Date putDate) {
        return this.picDao.updatePic(picId, picName, picPath, picSize, picDescribe,picType,putDate);
    }

    @Override
    public List<Pic> selectPicByType(String picType) {
        return this.picDao.selectPicByType(picType);
    }

    @Override
    public int selectPicNum(String picName) {
        return this.picDao.selectPicNum(picName);
    }
}
