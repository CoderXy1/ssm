package com.clothSale.service.impl;

import com.clothSale.mapper.UploadFileMapper;
import com.clothSale.model.UploadFile;
import com.clothSale.service.IUploadFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    @Resource
    private UploadFileMapper uploadFileMapper;

    @Override
    public int deleteByPrimaryKey(String fileId) {
        return uploadFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public int insert(UploadFile record) {
        return uploadFileMapper.insert(record);
    }

    @Override
    public int insertSelective(UploadFile record) {
        return uploadFileMapper.insertSelective(record);
    }

    @Override
    public UploadFile selectByPrimaryKey(String fileId) {
        return uploadFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int updateByPrimaryKeySelective(UploadFile record) {
        return uploadFileMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(UploadFile record) {
        return uploadFileMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(UploadFile record) {
        return uploadFileMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UploadFile> selectAllFile(String file_name, String file_type, int pageIndex, int pageSize) {
        return uploadFileMapper.selectAllFile(file_name, file_type, pageIndex, pageSize);
    }
}
