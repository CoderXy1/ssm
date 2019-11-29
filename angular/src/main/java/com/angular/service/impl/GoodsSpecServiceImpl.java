package com.angular.service.impl;

import com.angular.mapper.GoodsSpecMapper;
import com.angular.model.GoodsSpec;
import com.angular.service.IGoodsSpecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsSpecServiceImpl implements IGoodsSpecService {

    @Resource
    private GoodsSpecMapper goodsSpecMapper;

    @Override
    public int deleteByPrimaryKey(String specId) {
        return goodsSpecMapper.deleteByPrimaryKey(specId);
    }

    @Override
    public int insert(GoodsSpec record) {
        return goodsSpecMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsSpec record) {
        return goodsSpecMapper.insertSelective(record);
    }

    @Override
    public GoodsSpec selectByPrimaryKey(String specId) {
        return goodsSpecMapper.selectByPrimaryKey(specId);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsSpec record) {
        return goodsSpecMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsSpec record) {
        return goodsSpecMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsSpecAndValue(String spu_id) {
        return goodsSpecMapper.selectGoodsSpecAndValue(spu_id);
    }

    @Override
    public List<HashMap<String, Object>> selectAllGoodsSpec(String category_id) {
        return goodsSpecMapper.selectAllGoodsSpec(category_id);
    }

    @Override
    public int insertGoodsSpecSpu(String spu_id, String spec_id, Date gmt_create, Date gmt_update) {
        return goodsSpecMapper.insertGoodsSpecSpu(spu_id, spec_id, gmt_create, gmt_update);
    }

    @Override
    public int insertGoodsSpecCategory(String spec_id, String category_id, Date gmt_create, Date gmt_update) {
        return goodsSpecMapper.insertGoodsSpecCategory(spec_id, category_id, gmt_create, gmt_update);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsSpecValue(String spec_id) {
        return goodsSpecMapper.selectGoodsSpecValue(spec_id);
    }

    @Override
    public int insertGoodsSpecValue(String spec_value_id, String spec_id, String spec_value, Date gmt_create, Date gmt_update) {
        return goodsSpecMapper.insertGoodsSpecValue(spec_value_id, spec_id, spec_value, gmt_create,gmt_update);
    }

    @Override
    public String selectBySpecName(String spec_name) {
        return goodsSpecMapper.selectBySpecName(spec_name);
    }
}
