package com.resource.dao;

import com.resource.model.Music;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IMusicDao {

    List<Music> selectAllMusic();

    List<Music> selectMusicByPage(@Param("musicName") String musicName, @Param("singer")String singer, @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

    int insertMusic(@Param("musicId")int musicId, @Param("musicName")String musicName, @Param("singer")String singer, @Param("musicPath")String musicPath, @Param("musicSize")String musicSize, @Param("putDate")Date putDate);

    int selectMaxMusicId();

    int getMusicNum(@Param("musicName")String musicName);

}
