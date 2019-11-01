package com.auroraapp.service;

import com.auroraapp.model.File;

public interface IFileService {

    int deleteByPrimaryKey(String fileid);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String fileid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

}
