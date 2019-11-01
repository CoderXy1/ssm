package com.auroraapp.controller;


import com.auroraapp.model.File;
import com.auroraapp.service.IFileService;
import com.auroraapp.util.FIleUtil;
import com.auroraapp.util.ReduceImgTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class fileController {

    @Resource
    private IFileService fileService;

    @RequestMapping("/insertImage")
    @ResponseBody
    public int insertImage(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "fileId")String fileId) {

        String fileName = null;
        byte[] fileStr = null;

        try {
            fileName = file.getOriginalFilename();
            BASE64Encoder encoder = new BASE64Encoder();
            fileStr = ReduceImgTest.compressPicForScale(file.getBytes(),50);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File files = new File();
        files.setFileid(fileId);
        files.setFile(fileStr);
        files.setFilename(fileName);
        this.fileService.insert(files);
        return  1;
    }

    @RequestMapping("/insertFile")
    @ResponseBody
    public int insertFile(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "fileId")String fileId) {

        String fileName = null;
        byte[] fileStr = null;

        try {
            fileName = file.getOriginalFilename();
            BASE64Encoder encoder = new BASE64Encoder();
            fileStr = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File files = new File();
        files.setFileid(fileId);
        files.setFile(fileStr);
        files.setFilename(fileName);
        this.fileService.insert(files);
        return  1;
    }

    @RequestMapping("/selectImage")
    @ResponseBody
    public List<String> selectImage(@RequestParam(value = "fileId")String fileId) {

        List<String> list = new ArrayList<>();

        BASE64Encoder encoder = new BASE64Encoder();
        File file = this.fileService.selectByPrimaryKey(fileId);
        byte[] bytes = file.getFile();
        list.add(encoder.encode(bytes));

        return  list;

    }

    @RequestMapping("/downloadFile")
    @ResponseBody
    public int selectFile(@RequestParam(value = "fileId")String fileId,@RequestParam(value = "path")String path) {

        File file = this.fileService.selectByPrimaryKey(fileId);
        FIleUtil.getFile(file.getFile(),path,file.getFilename());

        return  1;

    }


}
