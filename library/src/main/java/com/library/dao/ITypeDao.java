package com.library.dao;

import com.library.model.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITypeDao {

    List<Type> selectAllTypes();

    Type selectTypeById(@Param("typeId")int typeId);

    Type selectTypeByName(@Param("typeName")String typeName);

}
