package com.example.springboottest.controller;

import com.example.springboottest.model.File;
import com.example.springboottest.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping("/getFile")
    @ResponseBody
    public File getFile(String fileId){
        return fileService.selectByPrimaryKey(fileId);
    }

}
