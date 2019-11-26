package com.auroraapp.service;

import com.auroraapp.model.Gallery;

import java.util.List;
import java.util.Map;

public interface IGalleryService {
    int deleteByPrimaryKey(String galleryid);

    int insert(Gallery record);

    int insertSelective(Gallery record);

    Gallery selectByPrimaryKey(String galleryid);

    int updateByPrimaryKeySelective(Gallery record);

    int updateByPrimaryKey(Gallery record);

    List<Map<String,Object>> selectAll(int pageIndex, int pageSize);

    int deleteGalleryByFileId(String fileId);
}