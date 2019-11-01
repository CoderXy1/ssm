package com.aurora.model;

import java.util.Date;

public class Gallery {
    private String galleryid;

    private String galleryname;

    private String fileid;

    private Date putdate;

    private String galleryDescribe;

    public String getGalleryid() {
        return galleryid;
    }

    public void setGalleryid(String galleryid) {
        this.galleryid = galleryid == null ? null : galleryid.trim();
    }

    public String getGalleryname() {
        return galleryname;
    }

    public void setGalleryname(String galleryname) {
        this.galleryname = galleryname == null ? null : galleryname.trim();
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid == null ? null : fileid.trim();
    }

    public Date getPutdate() {
        return putdate;
    }

    public void setPutdate(Date putdate) {
        this.putdate = putdate;
    }

    public String getGalleryDescribe() {
        return galleryDescribe;
    }

    public void setGalleryDescribe(String galleryDescribe) {
        this.galleryDescribe = galleryDescribe;
    }
}