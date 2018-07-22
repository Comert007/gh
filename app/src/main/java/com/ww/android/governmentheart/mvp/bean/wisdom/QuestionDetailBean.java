package com.ww.android.governmentheart.mvp.bean.wisdom;

import java.util.List;

/**
 * @author feng
 * @Date 2018/7/13.
 */
public class QuestionDetailBean {


    /**
     * isNewRecord : true
     * questionId : 1
     * title : 问题
     * content : 新建
     * questionDate : 1530704806000
     * questionUserId : 系统管理员
     * phone : 1390283659
     * status : 2
     * acceptUserId : 系统管理员
     * acceptDate : 1530704824000
     * units : 1
     * replyResult : 暂时不提供这种设施
     * replyDate : 2018-07-04 19:47:10.0
     * userAgent : 1
     * ip : 1
     * isOpen : 1
     * viewNum : 88
     * commentNum : 77
     * goodNum : 77
     * comment : [{"isNewRecord":true,"createDate":"2018-07-06 10:54:57","questionCommentId":"1",
     * "title":"问题","content":"EFE","qName":"系统管理员","status":"1","auditDate":"2018-08-01 10:55:02
     * ","userAgent":"1"}]
     */

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
    private String ip;
    private String isOpen;
    private int viewNum;
    private int commentNum;
    private int goodNum;
    private List<CommentBean> comment;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class CommentBean {
        /**
         * isNewRecord : true
         * createDate : 2018-07-06 10:54:57
         * questionCommentId : 1
         * title : 问题
         * content : EFE
         * qName : 系统管理员
         * status : 1
         * auditDate : 2018-08-01 10:55:02
         * userAgent : 1
         */

        private boolean isNewRecord;
        private String createDate;
        private String questionCommentId;
        private String title;
        private String content;
        private String qName;
        private String status;
        private String auditDate;
        private String userAgent;

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

        public String getQuestionCommentId() {
            return questionCommentId;
        }

        public void setQuestionCommentId(String questionCommentId) {
            this.questionCommentId = questionCommentId;
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

        public String getQName() {
            return qName;
        }

        public void setQName(String qName) {
            this.qName = qName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAuditDate() {
            return auditDate;
        }

        public void setAuditDate(String auditDate) {
            this.auditDate = auditDate;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }
    }
}
