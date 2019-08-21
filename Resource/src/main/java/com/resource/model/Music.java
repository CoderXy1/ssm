package com.resource.model;

import java.util.Date;

public class Music {

    private int musicId;
    private String musicName;
    private String singer;
    private String musicPath;
    private String musicSize;
    private Date putDate;

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(String musicSize) {
        this.musicSize = musicSize;
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }
}
