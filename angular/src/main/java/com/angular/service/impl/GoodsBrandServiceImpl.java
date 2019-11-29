package com.angular.service.impl;

import com.angular.mapper.GoodsBrandMapper;
import com.angular.model.GoodsBrand;
import com.angular.service.IGoodsBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsBrandServiceImpl implements IGoodsBrandService {

    @Resource
    private GoodsBrandMapper goodsBrandMapper;

    @Override
    public int deleteByPrimaryKey(String brandId) {
        return goodsBrandMapper.deleteByPrimaryKey(brandId);
    }

    @Override
    public int insert(GoodsBrand record) {
        return goodsBrandMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsBrand record) {
        return goodsBrandMapper.insertSelective(record);
    }

    @Override
    public GoodsBrand selectByPrimaryKey(String brandId) {
        return goodsBrandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsBrand record) {
        return goodsBrandMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsBrand record) {
        return goodsBrandMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String,Object>> selectGoodsBrand(int pageIndex, int pageSize, String brandName) {
        return goodsBrandMapper.selectGoodsBrand(pageIndex, pageSize, brandName);
    }
}
