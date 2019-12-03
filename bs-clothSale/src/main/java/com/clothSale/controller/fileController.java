package com.clothSale.controller;


import com.clothSale.model.UploadFile;
import com.clothSale.service.IUploadFileService;
import com.clothSale.util.ReduceImgTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/file")
public class fileController {

    @Resource
    private IUploadFileService uploadFileService;

    @RequestMapping("/insertImage")
    @ResponseBody
    public int insertImage(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "file_id") String file_id) {

        String fileName = null;
        byte[] fileStr = null;

        try {
            fileName = file.getOriginalFilename();
            fileStr = ReduceImgTest.compressPicForScale(file.getBytes(), 100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadFile files = new UploadFile();
        files.setFileId(file_id);
        files.setFileContent(fileStr);
        files.setFileName(fileName);
        files.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
        files.setPutDate(new Date());
        this.uploadFileService.insert(files);
        return 1;
    }

    @RequestMapping("/insertFile")
    @ResponseBody
    public int insertFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "file_id") String file_id) {

        String fileName = null;
        byte[] fileStr = null;

        try {
            fileName = file.getOriginalFilename();
            fileStr = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadFile files = new UploadFile();
        files.setFileId(file_id);
        files.setFileContent(fileStr);
        files.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
        files.setFileName(fileName);
        files.setPutDate(new Date());
        this.uploadFileService.insert(files);
        return 1;
    }

    @RequestMapping("/downloadFile")
    @ResponseBody
    public UploadFile downloadFile(@RequestParam(value = "fileId") String fileId) {

        return this.uploadFileService.selectByPrimaryKey(fileId);

    }

    @RequestMapping("/selectAllFile")
    @ResponseBody
    public List<UploadFile> selectAllFile(@RequestParam(value = "file_name") String file_name, @RequestParam(value = "file_type") String file_type, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {

        return this.uploadFileService.selectAllFile(file_name, file_type, pageIndex, pageSize);

    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public int deleteFile(@RequestParam(value = "file_id") String file_id) {

        return this.uploadFileService.deleteByPrimaryKey(file_id);

    }

}
