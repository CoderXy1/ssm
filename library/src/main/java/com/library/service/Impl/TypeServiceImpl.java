package com.library.service.Impl;

import com.library.dao.ITypeDao;
import com.library.model.Type;
import com.library.service.ITypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements ITypeService {

    @Resource
    private ITypeDao typeDao;

    @Override
    public List<Type> selectAllTypes() {
        return this.typeDao.selectAllTypes();
    }

    @Override
    public Type selectTypeById(int typeId) {
        return this.typeDao.selectTypeById(typeId);
    }

    @Override
    public Type selectTypeByName(String typeName) {
        return this.typeDao.selectTypeByName(typeName);
    }
}
