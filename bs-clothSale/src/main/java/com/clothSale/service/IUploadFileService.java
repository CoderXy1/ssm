package com.clothSale.service;

import com.clothSale.model.UploadFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUploadFileService {
    int deleteByPrimaryKey(String fileId);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKeyWithBLOBs(UploadFile record);

    int updateByPrimaryKey(UploadFile record);

    List<UploadFile> selectAllFile(String file_name,String file_type,int pageIndex,int pageSize);

}