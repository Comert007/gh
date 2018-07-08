package com.ww.android.governmentheart.mvp;

import android.support.annotation.Nullable;

import com.ww.android.governmentheart.mvp.bean.PagingBean;

import java.util.List;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class PageListBean<T> {

    @Nullable
    private List<T> list;
    @Nullable
    private PagingBean page;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagingBean getPage() {
        return page;
    }

    public void setPage(PagingBean page) {
        this.page = page;
    }
}
