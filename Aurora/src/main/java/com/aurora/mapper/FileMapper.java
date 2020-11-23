package com.aurora.mapper;


import com.aurora.model.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(String fileid);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String fileid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

    List<File> selectAllFile(@Param("fileName") String fileName, @Param("fileType") String fileType, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}