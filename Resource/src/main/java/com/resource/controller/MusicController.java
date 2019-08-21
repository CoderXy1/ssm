package com.resource.controller;

import com.resource.jsoup.musicJsoup;
import com.resource.model.Music;
import com.resource.service.IMusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Resource
    private IMusicService musicService;

    @RequestMapping("/selectMusicByPage")
    @ResponseBody
    public List<Music> selectMusicByPage(@RequestParam("musicName")String musicName, @RequestParam("singer")String singer, @RequestParam("pageIndex")int pageIndex, @RequestParam("pageSize")int pageSize) {
        return this.musicService.selectMusicByPage(musicName, singer, pageIndex, pageSize);
    }

    @RequestMapping("/selectMaxMusicId")
    @ResponseBody
    public int selectMaxMusicId() {
        return this.musicService.selectMaxMusicId();
    }

    @RequestMapping("/getMusicNum")
    @ResponseBody
    public int getMusicNum(@RequestParam("musicName")String musicName) {
        return this.musicService.getMusicNum(musicName);
    }


    @RequestMapping("/insertMusic")
    @ResponseBody
    public int insertMusic() {
        int musicId = 0;
        List<Music> list = musicJsoup.getText();
        for (Music music : list){
            musicId = this.selectMaxMusicId() + 1;
            this.musicService.insertMusic(musicId, music.getMusicName(), music.getSinger(), music.getMusicPath(), music.getMusicSize(),new Date());
        }
        return 1;
    }



}
