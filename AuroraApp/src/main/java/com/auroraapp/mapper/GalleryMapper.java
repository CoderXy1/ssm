package com.auroraapp.mapper;

import com.auroraapp.model.Gallery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GalleryMapper {
    int deleteByPrimaryKey(String galleryid);

    int insert(Gallery record);

    int insertSelective(Gallery record);

    Gallery selectByPrimaryKey(String galleryid);

    int updateByPrimaryKeySelective(Gallery record);

    int updateByPrimaryKey(Gallery record);

    List<Map<String,Object>> selectAll(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

    int deleteGalleryByFileId(@Param("fileId")String fileId);
}