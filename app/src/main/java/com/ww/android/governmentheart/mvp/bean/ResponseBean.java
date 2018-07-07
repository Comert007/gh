package com.ww.android.governmentheart.mvp.bean;

import android.support.annotation.Nullable;

import java.util.List;


public class ResponseBean<T> {

    private String status;
    private String msg;
    @Nullable
    private int totalNum;
    @Nullable
    private int totalPage;
    @Nullable
    private T datas;
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
    public T getDatas() {
        return datas;
    }

    public void setDatas(@Nullable T datas) {
        this.datas = datas;
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
