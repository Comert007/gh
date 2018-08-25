package com.ww.android.governmentheart.mvp.bean.wisdom;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class SuggestBean {


    /**
     * id : bbcdb23ab18d44e5a163c3a46c0d5777
     * isNewRecord : false
     * createDate : 2018-07-12 10:05:34
     * updateDate : 2018-07-12 10:05:34
     * title : 手机壳
     * proposalDate : 1531365413000
     * proposalUserId : 361
     * status : 1
     * viewNum : 0
     * commentNum : 0
     * goodNum : 0
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String title;
    private String acceptUserName;
    private long acceptDate;
    private long proposalDate;
    private String proposalUserId;
    private String status;
    private String viewNum;
    private String commentNum;
    private String goodNum;
    private String proposalUserName;

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

    public long getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(long proposalDate) {
        this.proposalDate = proposalDate;
    }

    public String getProposalUserId() {
        return proposalUserId;
    }

    public void setProposalUserId(String proposalUserId) {
        this.proposalUserId = proposalUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getProposalUserName() {
        return proposalUserName;
    }

    public String getAcceptUserName() {
        return acceptUserName;
    }

    public void setAcceptUserName(String acceptUserName) {
        this.acceptUserName = acceptUserName;
    }

    public long getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(long acceptDate) {
        this.acceptDate = acceptDate;
    }

    public void setProposalUserName(String proposalUserName) {
        this.proposalUserName = proposalUserName;
    }
}
