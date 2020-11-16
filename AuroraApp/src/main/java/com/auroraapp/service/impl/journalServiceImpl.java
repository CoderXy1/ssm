package com.auroraapp.service.impl;

import com.auroraapp.mapper.JournalMapper;
import com.auroraapp.model.Journal;
import com.auroraapp.service.IJournalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class journalServiceImpl implements IJournalService {

    @Resource
    private JournalMapper journalMapper;

    @Override
    public int deleteByPrimaryKey(String journalid) {
        return journalMapper.deleteByPrimaryKey(journalid);
    }

    @Override
    public int insert(Journal record) {
        return journalMapper.insert(record);
    }

    @Override
    public int insertSelective(Journal record) {
        return journalMapper.insertSelective(record);
    }

    @Override
    public Journal selectByPrimaryKey(String journalid) {
        return journalMapper.selectByPrimaryKey(journalid);
    }

    @Override
    public int updateByPrimaryKeySelective(Journal record) {
        return journalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Journal record) {
        return journalMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String,Object>> selectAll(int pageIndex, int pageSize) {
        return journalMapper.selectAll(pageIndex, pageSize);
    }

    @Override
    public Map<String, Object> selectSingle(String journalId) {
        return journalMapper.selectSingle(journalId);
    }
}
