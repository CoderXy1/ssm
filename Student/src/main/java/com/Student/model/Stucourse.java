package com.Student.model;

import java.util.Date;

public class Stucourse {
    private Integer scid;

    private String stuid;

    private String courseid;

    private Integer week;

    private String classroom;

    private Integer pitchnum;

    private Integer totalweeks;

    private Integer score;

    private Date scdate;

    private String ispass;

    private String isrebulid;

    private String term;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public Integer getPitchnum() {
        return pitchnum;
    }

    public void setPitchnum(Integer pitchnum) {
        this.pitchnum = pitchnum;
    }

    public Integer getTotalweeks() {
        return totalweeks;
    }

    public void setTotalweeks(Integer totalweeks) {
        this.totalweeks = totalweeks;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getScdate() {
        return scdate;
    }

    public void setScdate(Date scdate) {
        this.scdate = scdate;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass == null ? null : ispass.trim();
    }

    public String getIsrebulid() {
        return isrebulid;
    }

    public void setIsrebulid(String isrebulid) {
        this.isrebulid = isrebulid == null ? null : isrebulid.trim();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }
}