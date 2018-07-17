package com.ww.android.governmentheart.mvp.bean.wisdom;

import java.util.List;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class SuggestDetailBean {

    /**
     * title:””,cont:””,status:””,statusName:””,viewNum:””,commentNum:””,date:””,dealName:””,
     * dealDate:””，id：“”,dealResult:””，imgs：[{fileId:””,suffix:””,name:’’
     */

    private String title;
    private String content;
    private String status;
    private String statusName;
    private String viewNum;
    private String commentNum;
    private String date;
    private String dealName;
    private String dealDate;
    private String id;
    private String dealResult;
    private List<ImagePickBean> imgs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public List<ImagePickBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImagePickBean> imgs) {
        this.imgs = imgs;
    }
}
