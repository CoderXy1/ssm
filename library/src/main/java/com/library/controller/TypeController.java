package com.library.controller;

import com.library.dao.ITypeDao;
import com.library.model.Book;
import com.library.model.Type;
import com.library.service.IBookService;
import com.library.service.ITypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Resource
    private ITypeService typeService;

    @RequestMapping("/selectAllTypes")
    @ResponseBody
    public List<Type> selectAllTypes() throws Exception{

        List<Type> list= this.typeService.selectAllTypes();

        return list;
    }

    @RequestMapping("/selectTypeById")
    @ResponseBody
    public Type selectTypeNameById(@RequestParam("typeId")int typeId) throws Exception{

        return this.typeService.selectTypeById(typeId);

    }

    @RequestMapping("/selectTypeByName")
    @ResponseBody
    public Type selectIdByName(@RequestParam("typeName")String typeName) throws Exception{

        return this.typeService.selectTypeByName(typeName);

    }




}
