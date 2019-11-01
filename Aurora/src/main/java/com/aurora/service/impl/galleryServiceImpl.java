package com.aurora.service.impl;

import com.aurora.mapper.GalleryMapper;
import com.aurora.model.Gallery;
import com.aurora.service.IGalleryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class galleryServiceImpl implements IGalleryService {

    @Resource
    private GalleryMapper galleryMapper;

    @Override
    public int deleteByPrimaryKey(String galleryid) {
        return galleryMapper.deleteByPrimaryKey(galleryid);
    }

    @Override
    public int insert(Gallery record) {
        return galleryMapper.insert(record);
    }

    @Override
    public int insertSelective(Gallery record) {
        return galleryMapper.insertSelective(record);
    }

    @Override
    public Gallery selectByPrimaryKey(String galleryid) {
        return galleryMapper.selectByPrimaryKey(galleryid);
    }

    @Override
    public int updateByPrimaryKeySelective(Gallery record) {
        return galleryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Gallery record) {
        return galleryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> selectAll(int pageIndex, int pageSize) {
        return galleryMapper.selectAll(pageIndex, pageSize);
    }
}
