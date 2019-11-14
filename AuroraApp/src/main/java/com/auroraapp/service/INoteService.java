package com.auroraapp.service;

import com.auroraapp.model.Note;

import java.util.List;
import java.util.Map;

public interface INoteService {

    int deleteByPrimaryKey(Integer noteid);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer noteid);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    List<Map<String,Object>> selectAll(int pageIndex, int pageSize);

}
