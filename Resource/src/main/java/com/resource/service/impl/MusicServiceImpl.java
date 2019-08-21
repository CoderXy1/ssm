package com.resource.service.impl;

import com.resource.dao.IMusicDao;
import com.resource.model.Music;
import com.resource.service.IMusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MusicServiceImpl implements IMusicService {

    @Resource
    private IMusicDao musicDao;

    @Override
    public List<Music> selectAllMusic() {
        return this.musicDao.selectAllMusic();
    }

    @Override
    public List<Music> selectMusicByPage(String musicName, String singer, int pageIndex, int pageSize) {
        return this.musicDao.selectMusicByPage(musicName, singer, pageIndex, pageSize);
    }

    @Override
    public int insertMusic(int musicId, String musicName, String singer, String musicPath, String musicSize, Date putDate) {
        return this.musicDao.insertMusic(musicId, musicName, singer, musicPath, musicSize,putDate);
    }

    @Override
    public int selectMaxMusicId() {
        return this.musicDao.selectMaxMusicId();
    }

    @Override
    public int getMusicNum(String musicIName) {
        return this.musicDao.getMusicNum(musicIName);
    }
}
