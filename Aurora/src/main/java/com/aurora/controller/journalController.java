package com.aurora.controller;


import com.aurora.model.Journal;
import com.aurora.service.IJournalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/journal")
public class journalController {

    @Resource
    private IJournalService journalService;

    @RequestMapping("/selectJournal")
    @ResponseBody
    public Journal selectByPrimaryKey(@RequestParam("journalid") String journalid) {
        return journalService.selectByPrimaryKey(journalid);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String,Object>> selectAll(@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize")int pageSize) {
        return journalService.selectAll(pageIndex, pageSize);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public int upload(@RequestParam(value = "file") MultipartFile file) {

        /*try (FileInputStream in = (FileInputStream) file.getInputStream();
             FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator.SC-201907111318\\Desktop")) {

            //保存文件到filePathAndName
            int hasRead = 0;
            byte[] bytes = new byte[1024];
            while ((hasRead = in.read(bytes)) > 0) {
                out.write(bytes, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = file.getInputStream();
            fileName = file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = "C:\\Users\\Administrator.SC-201907111318\\Desktop";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  0;
    }

}
