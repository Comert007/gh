package com.ww.android.governmentheart.mvp.bean.wisdom;

/**
 * @author feng
 * @Date 2018/7/13.
 */
public class QuestionBean {


    /**
     * isNewRecord : true
     * questionId : 2
     * title : 问题
     * content : 问题
     * questionDate : 1530949271000
     * questionUserId : 系统管理员
     * phone : 15902834590
     * status : 2
     * acceptUserId : 系统管理员
     * acceptDate : 1531381294000
     * units : 1
     * replyResult : 回复结果
     * replyDate : 2018-07-07 15:41:41.0
     * userAgent : 1
     * isOpen : 1
     * viewNum : 1
     * commentNum : 1
     * goodNum : 1
     */

    private String id;
    private boolean isNewRecord;
    private String questionId;
    private String title;
    private String content;
    private String questionDate;
    private String questionUserId;
    private String phone;
    private String status;
    private String acceptUserId;
    private long acceptDate;
    private String units;
    private String replyResult;
    private String replyDate;
    private String userAgent;
    private String isOpen;
    private String image;
    private int viewNum;
    private int commentNum;
    private int goodNum;

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

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

    public String getQuestionDate() {
        return questionDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public String getQuestionUserId() {
        return questionUserId;
    }

    public void setQuestionUserId(String questionUserId) {
        this.questionUserId = questionUserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public long getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(long acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getReplyResult() {
        return replyResult;
    }

    public void setReplyResult(String replyResult) {
        this.replyResult = replyResult;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
