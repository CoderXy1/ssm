package com.clothSale.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ActivityInfo {
    private String activityId;

    private String activityName;

    private String activityDescribe;

    private Integer activityState;

    private Date activityDateBegin;

    private Date activityDateEnd;

    private Date gmtCreate;

    private Date gmtUpdate;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityDescribe() {
        return activityDescribe;
    }

    public void setActivityDescribe(String activityDescribe) {
        this.activityDescribe = activityDescribe == null ? null : activityDescribe.trim();
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public Date getActivityDateBegin() {
        return activityDateBegin;
    }

    public void setActivityDateBegin(Date activityDateBegin) {
        this.activityDateBegin = activityDateBegin;
    }

    public Date getActivityDateEnd() {
        return activityDateEnd;
    }

    public void setActivityDateEnd(Date activityDateEnd) {
        this.activityDateEnd = activityDateEnd;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}