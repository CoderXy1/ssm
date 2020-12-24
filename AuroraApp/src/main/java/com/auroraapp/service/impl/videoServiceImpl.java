package com.auroraapp.service.impl;

import com.auroraapp.mapper.VideoMapper;
import com.auroraapp.model.Video;
import com.auroraapp.service.IVideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class videoServiceImpl implements IVideoService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public int deleteByPrimaryKey(String videoid) {
        return videoMapper.deleteByPrimaryKey(videoid);
    }

    @Override
    public int insert(Video record) {
        return videoMapper.insert(record);
    }

    @Override
    public int insertSelective(Video record) {
        return videoMapper.insertSelective(record);
    }

    @Override
    public Video selectByPrimaryKey(String videoid) {
        return videoMapper.selectByPrimaryKey(videoid);
    }

    @Override
    public int updateByPrimaryKeySelective(Video record) {
        return videoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Video record) {
        return videoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> selectAll(int pageIndex, int pageSize) {
        return videoMapper.selectAll(pageIndex, pageSize);
    }

    @Override
    public Map<String, Object> selectSingle(String videoId) {
        return videoMapper.selectSingle(videoId);
    }
}
