package com.auroraapp.controller;


import com.auroraapp.model.Journal;
import com.auroraapp.service.IFileService;
import com.auroraapp.service.IJournalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/journal")
public class journalController {

    @Resource
    private IJournalService journalService;

    @Resource
    private IFileService fileService;

    @RequestMapping("/selectSingle")
    @ResponseBody
    public Map<String, Object> selectByPrimaryKey(@RequestParam("journalId") String journalId) {
        return journalService.selectSingle(journalId);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String,Object>> selectAll(@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize")int pageSize) {
        return journalService.selectAll(pageIndex, pageSize);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(@RequestParam("journalId") String journalId,@RequestParam(required = false)String title,@RequestParam(required = false)String content,
                                                                @RequestParam(required = false)String weather,@RequestParam(required = false)int temperature,@RequestParam(required = false)String fileId) {
        Journal journal = new Journal();
        journal.setJournalid(journalId);
        journal.setTitle(title);
        journal.setContent(content);
        journal.setTemperature(temperature);
        journal.setWeather(weather);
        journal.setFileid(fileId);
        return journalService.updateByPrimaryKeySelective(journal);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public int insert(@RequestParam("journalId") String journalId,@RequestParam("title")String title,@RequestParam("content")String content,
                      @RequestParam("weather")String weather,@RequestParam("temperature")int temperature,@RequestParam("fileId")String fileId) {
        Journal journal = new Journal();
        journal.setJournalid(journalId);
        journal.setTitle(title);
        journal.setContent(content);
        journal.setTemperature(temperature);
        journal.setWeather(weather);
        journal.setFileid(fileId);
        journal.setPutdate(new Date());
        return journalService.insert(journal);
    }

    @RequestMapping("/deleteJournal")
    @ResponseBody
    public int deleteJournal(@RequestParam("journalId") String journalId,@RequestParam("fileId")String fileId) {
        fileService.deleteByPrimaryKey(fileId);
        return journalService.deleteByPrimaryKey(journalId);
    }


}
