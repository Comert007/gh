package com.ww.android.governmentheart.mvp.bean.heart;

import com.ww.android.governmentheart.mvp.bean.MultipleBean;

public class NewsBean extends MultipleBean {

    //{url:"",title:"",imageUrl:"",viewNum:"",commentNum:"",date:"",code："",id：""}
    private String url;
    private String title;
    private String imageUrl;
    private String viewNum;
    private String commentNum;
    private String date;
    private String code;
    private String id;

    public NewsBean(int itemType) {
        super(itemType);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
