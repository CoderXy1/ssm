package com.example.springboottest.service.serviceImpl;

import com.example.springboottest.mapper.FileMapper;
import com.example.springboottest.model.File;
import com.example.springboottest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IFileService implements FileService {

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
}
