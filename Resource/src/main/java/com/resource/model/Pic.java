package com.resource.model;

import java.util.Date;

public class Pic {

    private int picId;
    private String picName;
    private String picPath;
    private String picSize;
    private String picDescribe;
    private String picType;
    private Date putDate;

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicSize() {
        return picSize;
    }

    public void setPicSize(String picSize) {
        this.picSize = picSize;
    }

    public String getPicDescribe() {
        return picDescribe;
    }

    public void setPicDescribe(String picDescribe) {
        this.picDescribe = picDescribe;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }
}
