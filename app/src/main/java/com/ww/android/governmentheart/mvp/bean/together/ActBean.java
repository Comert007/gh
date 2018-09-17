package com.ww.android.governmentheart.mvp.bean.together;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class ActBean {
    /**
     * {title:"",status:"",statusName:"",viewNum:"",commentNum:"",date:""，startDate:"",endDate:"",address:"",url:"",id:""}
     */

    private String title;
    private String status;
    private String statusName;
    private String viewNum;
    private String commentNum;
    private String date;
    private long startDate;
    private long endDate;
    private String address;
    private String url;
    private String sponsor;
    private String id;
    private String type;
    private String litimg;

    public String getLitimg() {
        return litimg;
    }

    public void setLitimg(String litimg) {
        this.litimg = litimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
}
