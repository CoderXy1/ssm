package com.library.service.Impl;

import com.library.dao.IProvinceDao;
import com.library.model.Province;
import com.library.service.IProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("provinceService")
public class ProvinceServiceImpl implements IProvinceService {

    @Resource
    private IProvinceDao provinceDao;

    @Override
    public List<Province> selectAllProvince() {
        return this.provinceDao.selectAllProvince();
    }
}
