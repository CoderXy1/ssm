package com.resource.service;

import com.resource.model.Music;

import java.util.Date;
import java.util.List;

public interface IMusicService {

    List<Music> selectAllMusic();

    List<Music> selectMusicByPage(String musicName,String singer,int pageIndex,int pageSize);

    int insertMusic(int musicId, String musicName, String singer, String musicPath, String musicSize, Date putDate);

    int selectMaxMusicId();

    int getMusicNum(String musicName);

}
