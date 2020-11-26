package com.auroraapp.controller;

import com.auroraapp.model.File;
import com.auroraapp.service.IFileService;
import com.auroraapp.service.IGalleryService;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/file")
public class fileController {

    @Resource
    private IFileService fileService;

    @Resource
    private IGalleryService galleryService;

    private String FilePath = "/root/AuroraApp/Resource/";

    private String Url = "http://www.shanshaoxy.cn/AuroraApp/Resource/";

    @RequestMapping("/insertImage")
    @ResponseBody
    public int insertImage(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "fileId")String fileId) {

        String fileName = null;
        String fileStr = null;
        String date = null;

        try {
            fileName = file.getOriginalFilename();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            date = df.format(new Date());
            fileStr =  FilePath + "Photo/" + date;
            java.io.File filePath = new java.io.File(fileStr);
            if(!filePath.exists()){//如果文件夹不存在
                filePath.mkdir();//创建文件夹
            }
            fileStr =  fileStr +  "/" + fileName;
            FileOutputStream out = new FileOutputStream(fileStr);
            out.write(ReduceImgTest.compressPicForScale(file.getBytes(),1000));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File files = new File();
        files.setFileid(fileId);
        files.setFile(Url + "Photo/" + date + "/" + fileName);
        files.setFilename(fileName);
        files.setFiletype(fileName.substring(fileName.lastIndexOf(".") + 1));
        this.fileService.insert(files);
        return  1;
    }

    @RequestMapping("/insertFile")
    @ResponseBody
    public int insertFile(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "fileId")String fileId) {

        String fileName = null;
        String fileStr = null;
        String date = null;

        try {
            fileName = file.getOriginalFilename();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            date = df.format(new Date());
            fileStr =  FilePath + "File/" + date;
            java.io.File filePath = new java.io.File(fileStr);
            if(!filePath.exists()){//如果文件夹不存在
                filePath.mkdir();//创建文件夹
            }
            fileStr =  fileStr +  "/" +  fileName;
            FileOutputStream out = new FileOutputStream(fileStr);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File files = new File();
        files.setFileid(fileId);
        files.setFile(Url + "File/" + date + "/" + fileName);
        files.setFilename(fileName);
        files.setFiletype(fileName.substring(fileName.lastIndexOf(".") + 1));
        this.fileService.insert(files);
        return  1;
    }

    @RequestMapping("/selectImage")
    @ResponseBody
    public List<String> selectImage(@RequestParam(value = "fileId")String fileId) {

        List<String> list = new ArrayList<>();

        BASE64Encoder encoder = new BASE64Encoder();
        File file = this.fileService.selectByPrimaryKey(fileId);
        //byte[] bytes = file.getFile();
        //list.add(encoder.encode(bytes));

        return list;


    }

    @RequestMapping("/downloadFile")
    @ResponseBody
    public File downloadFile(@RequestParam(value = "fileId")String fileId) {

        return this.fileService.selectByPrimaryKey(fileId);

    }

    @RequestMapping("/selectAllFile")
    @ResponseBody
    public List<File> selectAllFile(@RequestParam(value = "fileName")String fileName,@RequestParam(value = "fileType")String fileType,@RequestParam(value = "pageIndex")int pageIndex,@RequestParam(value = "pageSize")int pageSize) {

        return this.fileService.selectAllFile(fileName,fileType,pageIndex,pageSize);

    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public int deleteFile(@RequestParam(value = "fileId")String fileId) {

        this.galleryService.deleteGalleryByFileId(fileId);
        return this.fileService.deleteByPrimaryKey(fileId);

    }

}
