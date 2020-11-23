package com.aurora.service.impl;


import com.aurora.mapper.FileMapper;
import com.aurora.model.File;
import com.aurora.service.IFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class fileServiceImpl implements IFileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public int deleteByPrimaryKey(String fileid) {
        return fileMapper.deleteByPrimaryKey(fileid);
    }

    @Override
    public int insert(File record) {
        return fileMapper.insert(record);
    }

    @Override
    public int insertSelective(File record) {
        return fileMapper.insertSelective(record);
    }

    @Override
    public File selectByPrimaryKey(String fileid) {
        return fileMapper.selectByPrimaryKey(fileid);
    }

    @Override
    public int updateByPrimaryKeySelective(File record) {
        return fileMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(File record) {
        return fileMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<File> selectAllFile(String fileName, String fileType, int pageIndex, int pageSize) {
        return fileMapper.selectAllFile(fileName, fileType, pageIndex, pageSize);
    }
}
