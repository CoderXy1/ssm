package com.Student.service.impl;

import com.Student.mapper.MajorMapper;
import com.Student.model.Major;
import com.Student.service.IMajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MajorServiceImpl implements IMajorService {

    @Resource
    private MajorMapper majorMapper;

    @Override
    public int deleteByPrimaryKey(Integer majorid) {
        return this.majorMapper.deleteByPrimaryKey(majorid);
    }

    @Override
    public int insert(Major record) {
        return this.majorMapper.insert(record);
    }

    @Override
    public int insertSelective(Major record) {
        return this.majorMapper.insertSelective(record);
    }

    @Override
    public Major selectByPrimaryKey(Integer majorid) {
        return this.majorMapper.selectByPrimaryKey(majorid);
    }

    @Override
    public int updateByPrimaryKeySelective(Major record) {
        return this.majorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Major record) {
        return this.majorMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Major> selectMajorByDepId(int depId) {
        return this.majorMapper.selectMajorByDepId(depId);
    }
}
