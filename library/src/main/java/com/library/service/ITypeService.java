package com.library.service;

import com.library.model.Type;

import java.util.List;

public interface ITypeService {

    List<Type> selectAllTypes();

    Type selectTypeById(int typeId);

    Type selectTypeByName(String typeName);


}
