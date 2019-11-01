package com.aurora.model;

import java.util.Date;

public class Journal {
    private String journalid;

    private String title;

    private String content;

    private Date putdate;

    private String weather;

    private Integer temperature;

    private String fileid;

    public String getJournalid() {
        return journalid;
    }

    public void setJournalid(String journalid) {
        this.journalid = journalid == null ? null : journalid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPutdate() {
        return putdate;
    }

    public void setPutdate(Date putdate) {
        this.putdate = putdate;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid == null ? null : fileid.trim();
    }
}