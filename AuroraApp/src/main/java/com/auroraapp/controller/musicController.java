package com.auroraapp.controller;


import com.auroraapp.model.Music;
import com.auroraapp.model.Video;
import com.auroraapp.service.IFileService;
import com.auroraapp.service.IMusicService;
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
@RequestMapping("/music")
public class musicController {

    @Resource
    private IMusicService musicService;

    @Resource
    private IFileService fileService;

    @RequestMapping("/selectSingle")
    @ResponseBody
    public Map<String, Object> selectByPrimaryKey(@RequestParam("musicId") String musicId) {
        return musicService.selectSingle(musicId);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String, Object>> selectAll(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return musicService.selectAll(pageIndex, pageSize);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(@RequestParam("musicId") String musicId, @RequestParam(required = false) String musicName, @RequestParam(required = false) String singer, @RequestParam(required = false) String fileId) {
        Music music = new Music();
        music.setMusicid(musicId);
        music.setMusicname(musicName);
        music.setSinger(singer);
        return musicService.updateByPrimaryKeySelective(music);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public int insert(@RequestParam("musicId") String musicId, @RequestParam("musicName") String musicName, @RequestParam("singer") String singer, @RequestParam("fileId") String fileId) {
        Music music = new Music();
        music.setMusicid(musicId);
        music.setMusicname(musicName);
        music.setSinger(singer);
        music.setFileid(fileId);
        music.setPutdate(new Date());
        return musicService.insert(music);
    }

    @RequestMapping("/deleteVideo")
    @ResponseBody
    public int deleteJournal(@RequestParam("musicId") String musicId, @RequestParam("fileId") String fileId) {
        fileService.deleteByPrimaryKey(fileId);
        return musicService.deleteByPrimaryKey(musicId);
    }


}
