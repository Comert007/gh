package com.ww.android.governmentheart.mvp.bean;

import java.util.List;

import javax.annotation.Nullable;

public class ResponseBean<T> {

    private String status;
    private String msg;

    @Nullable
    private T data;
    @Nullable
    private PageBean<T> page;
    @Nullable
    private List<T> list;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }

    @Nullable
    public PageBean<T> getPage() {
        return page;
    }

    public void setPage(@Nullable PageBean<T> page) {
        this.page = page;
    }

    @Nullable
    public List<T> getList() {
        return list;
    }

    public void setList(@Nullable List<T> list) {
        this.list = list;
    }
}
