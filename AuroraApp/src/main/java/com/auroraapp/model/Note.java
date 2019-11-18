package com.auroraapp.model;

import java.util.Date;

public class Note {
    private String noteid;

    private String notetitle;

    private String content;

    private Date notedatetime;

    private String notecolor;

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle == null ? null : notetitle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getNotedatetime() {
        return notedatetime;
    }

    public void setNotedatetime(Date notedatetime) {
        this.notedatetime = notedatetime;
    }

    public String getNotecolor() {
        return notecolor;
    }

    public void setNotecolor(String notecolor) {
        this.notecolor = notecolor == null ? null : notecolor.trim();
    }
}