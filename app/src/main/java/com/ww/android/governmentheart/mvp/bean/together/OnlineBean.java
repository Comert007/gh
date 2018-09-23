package com.ww.android.governmentheart.mvp.bean.together;

/**
 * @Author feng
 * @Date 2018/7/11
 */
public class OnlineBean {


    /**
     * id : 12
     * isNewRecord : false
     * createDate : 2018-09-22 20:14:13
     * updateDate : 2018-09-22 20:14:16
     * title : 测试直播
     * videoUrl : 2112
     * status : 3
     * activityId : 34
     * viewNum : 34
     * commentNum : 34
     * goodNum : 34
     * image : 34
     * pushUrl : rtmp://video-center-bj.alivecdn.com/abc/php?vhost=tv.yizykj
     * .com&auth_key=1537625999-0-0-7bb91ba45e0f5fc2a607770cefa2c657
     * pullRtmpUrl : rtmp://tv.yizykj
     * .com/abc/php?auth_key=1537625999-0-0-7bb91ba45e0f5fc2a607770cefa2c657
     * pullFlvUrl : http://tv.yizykj.com/abc/php
     * .flv?auth_key=1537625999-0-0-5b175528d12bb99d46b8b179b2cdb023
     * pullM3u8Url : http://tv.yizykj.com/abc/php.m3u8?auth_key=1537625999-0-0-babaf3b85f93e4c69ab559161bee9904
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String title;
    private String videoUrl;
    private String status;
    private String activityId;
    private int viewNum;
    private int commentNum;
    private int goodNum;
    private String image;
    private String pushUrl;
    private String pullRtmpUrl;
    private String pullFlvUrl;
    private String pullM3u8Url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getPullRtmpUrl() {
        return pullRtmpUrl;
    }

    public void setPullRtmpUrl(String pullRtmpUrl) {
        this.pullRtmpUrl = pullRtmpUrl;
    }

    public String getPullFlvUrl() {
        return pullFlvUrl;
    }

    public void setPullFlvUrl(String pullFlvUrl) {
        this.pullFlvUrl = pullFlvUrl;
    }

    public String getPullM3u8Url() {
        return pullM3u8Url;
    }

    public void setPullM3u8Url(String pullM3u8Url) {
        this.pullM3u8Url = pullM3u8Url;
    }
}
