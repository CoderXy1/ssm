package com.aurora.service;

import com.aurora.model.Journal;

import java.util.List;
import java.util.Map;

public interface IJournalService {

    int deleteByPrimaryKey(String journalid);

    int insert(Journal record);

    int insertSelective(Journal record);

    Journal selectByPrimaryKey(String journalid);

    int updateByPrimaryKeySelective(Journal record);

    int updateByPrimaryKey(Journal record);

    List<Map<String,Object>> selectAll(int pageIndex, int pageSize);

}
