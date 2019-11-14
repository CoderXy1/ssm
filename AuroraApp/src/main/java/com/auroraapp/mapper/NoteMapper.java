package com.auroraapp.mapper;

import com.auroraapp.model.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer noteid);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer noteid);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    List<Map<String,Object>> selectAll(@Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize);
}