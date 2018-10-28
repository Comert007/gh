package com.ww.android.governmentheart.mvp.bean.work;

import android.support.annotation.Nullable;

import java.util.List;

public class ThemeReplyEntity {


    /**
     * id : 2
     * isNewRecord : false
     * content : 吃的什么
     * fatherId : 1
     * topicId : 1
     * replyDate : 1539826604000
     * userId : 3fc15006a7eb40fba6a227f62b10845c
     * userName : 谢红林
     * officeId : 4c943a7d4cbb4e789e4f457a0e6a9e90
     * officeName : 联络科
     * fatherUserName : 施文
     */

    private String id;
    private boolean isNewRecord;
    private String content;
    private String fatherId;
    private String topicId;
    private long replyDate;
    private String userId;
    private String userName;
    private String officeId;
    private String officeName;
    @Nullable
    private String fatherUserName;
    @Nullable
    private List<ThemeReplyEntity> replyList;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public long getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(long replyDate) {
        this.replyDate = replyDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getFatherUserName() {
        return fatherUserName;
    }

    public void setFatherUserName(String fatherUserName) {
        this.fatherUserName = fatherUserName;
    }

    public List<ThemeReplyEntity> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ThemeReplyEntity> replyList) {
        this.replyList = replyList;
    }
}
