package com.ww.android.governmentheart.mvp.bean.login;

import android.support.annotation.Nullable;

import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public class NewsTypeBean extends MultipleBean implements Serializable{


    /*
     * code:1 方针，2 统战知识，3 权威解读， 4 政策库 5 崇州特色 6 农副产品
     * 7 电商介绍 8 人物访谈 9加入我（加入组织）
     * ac：是否可评论  0否1是
     */

    private String id;
    private String name;
    private String code;
    private int ac;
    @Nullable
    private List<NewsBean> news;

    public NewsTypeBean(int itemType) {
        super(itemType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    @Nullable
    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(@Nullable List<NewsBean> news) {
        this.news = news;
    }
}
