package com.auroraapp.controller;


import com.auroraapp.model.Journal;
import com.auroraapp.model.Video;
import com.auroraapp.service.IFileService;
import com.auroraapp.service.IJournalService;
import com.auroraapp.service.IVideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class videoController {

    @Resource
    private IVideoService videoService;

    @Resource
    private IFileService fileService;

    @RequestMapping("/selectSingle")
    @ResponseBody
    public Map<String, Object> selectByPrimaryKey(@RequestParam("videoId") String videoId) {
        return videoService.selectSingle(videoId);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String,Object>> selectAll(@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize")int pageSize) {
        return videoService.selectAll(pageIndex, pageSize);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(@RequestParam("videoId") String videoId,@RequestParam(required = false)String title,@RequestParam(required = false)String fileId) {
        Video video = new Video();
        video.setVideoid(videoId);
        video.setTitle(title);
        video.setFileid(fileId);
        return videoService.updateByPrimaryKeySelective(video);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public int insert(@RequestParam("videoId") String videoId,@RequestParam("title")String title,@RequestParam("fileId")String fileId) {
        Video video = new Video();
        video.setVideoid(videoId);
        video.setTitle(title);
        video.setFileid(fileId);
        video.setPutdate(new Date());
        return videoService.insert(video);
    }

    @RequestMapping("/deleteVideo")
    @ResponseBody
    public int deleteJournal(@RequestParam("videoId") String videoId,@RequestParam("fileId")String fileId) {
        fileService.deleteByPrimaryKey(fileId);
        return videoService.deleteByPrimaryKey(videoId);
    }


}
