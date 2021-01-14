package com.auroraapp.service.impl;

import com.auroraapp.mapper.NoteMapper;
import com.auroraapp.model.Note;
import com.auroraapp.service.INoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class noteServiceImpl implements INoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public int deleteByPrimaryKey(String noteid) {
        return noteMapper.deleteByPrimaryKey(noteid);
    }

    @Override
    public int insert(Note record) {
        return noteMapper.insert(record);
    }

    @Override
    public int insertSelective(Note record) {
        return noteMapper.insertSelective(record);
    }

    @Override
    public Note selectByPrimaryKey(String noteid) {
        return noteMapper.selectByPrimaryKey(noteid);
    }

    @Override
    public int updateByPrimaryKeySelective(Note record) {
        return noteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Note record) {
        return noteMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> selectAll(int pageIndex, int pageSize) {
        return noteMapper.selectAll(pageIndex,pageSize);
    }

}
