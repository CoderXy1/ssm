package com.aurora.mapper;

import com.aurora.model.Journal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JournalMapper {
    int deleteByPrimaryKey(String journalid);

    int insert(Journal record);

    int insertSelective(Journal record);

    Journal selectByPrimaryKey(String journalid);

    int updateByPrimaryKeySelective(Journal record);

    int updateByPrimaryKey(Journal record);

    List<Map<String,Object>> selectAll(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}