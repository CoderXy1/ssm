package com.auroraapp.service.impl;

import com.auroraapp.mapper.MusicMapper;
import com.auroraapp.model.Music;
import com.auroraapp.service.IMusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class musicServiceImpl implements IMusicService {

    @Resource
    private MusicMapper musicMapper;

    @Override
    public int deleteByPrimaryKey(String musicid) {
        return musicMapper.deleteByPrimaryKey(musicid);
    }

    @Override
    public int insert(Music record) {
        return musicMapper.insert(record);
    }

    @Override
    public int insertSelective(Music record) {
        return musicMapper.insertSelective(record);
    }

    @Override
    public Music selectByPrimaryKey(String musicid) {
        return musicMapper.selectByPrimaryKey(musicid);
    }

    @Override
    public int updateByPrimaryKeySelective(Music record) {
        return musicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Music record) {
        return musicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> selectAll(int pageIndex, int pageSize) {
        return musicMapper.selectAll(pageIndex, pageSize);
    }

    @Override
    public Map<String, Object> selectSingle(String journalId) {
        return musicMapper.selectSingle(journalId);
    }
}
