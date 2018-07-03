package com.ww.android.governmentheart.mvp.bean;

import java.util.List;

public class PageBean<T> {


    /**
     * pageNo : 1
     * pageSize : 10
     * count : 100
     */

    private int pageNo;
    private int pageSize;
    private int count;
    private List<T> list;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
