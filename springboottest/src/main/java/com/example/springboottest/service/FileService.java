package com.example.springboottest.service;

import com.example.springboottest.model.File;
import org.springframework.stereotype.Service;

public interface FileService {

        int deleteByPrimaryKey(String fileid);

        int insert(File record);

        int insertSelective(File record);

        File selectByPrimaryKey(String fileid);

        int updateByPrimaryKeySelective(File record);

        int updateByPrimaryKey(File record);
}
