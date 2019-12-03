package com.clothSale.mapper;

import com.clothSale.model.UploadFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadFileMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKeyWithBLOBs(UploadFile record);

    int updateByPrimaryKey(UploadFile record);

    List<UploadFile> selectAllFile(@Param("file_name")String file_name, @Param("file_type") String file_type, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

}