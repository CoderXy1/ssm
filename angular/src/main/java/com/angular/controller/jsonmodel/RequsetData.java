package com.angular.controller.jsonmodel;

import java.util.Map;

public class RequsetData<T> {

    private T item;
    private Map<String, Object> extdata;
    private Boolean success;
    private String msg;

    public RequsetData() {
        this.success = true;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return this.item;
    }

    public Map<String, Object> getExtdata() {
        return extdata;
    }

    public void setExtdata(Map<String, Object> extdata) {
        this.extdata = extdata;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
