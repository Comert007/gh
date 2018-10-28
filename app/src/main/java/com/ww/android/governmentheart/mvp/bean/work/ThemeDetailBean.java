package com.ww.android.governmentheart.mvp.bean.work;

public class ThemeDetailBean {


    /**
     * id : 1
     * isNewRecord : false
     * title : 你吃了吗
     * content : 话题内容
     * beginTime : 1539770891000
     * endTime : 1540980487000
     * userId : 3fc15006a7eb40fba6a227f62b10845c
     * officeId : 4c943a7d4cbb4e789e4f457a0e6a9e90
     * officeName : 联络科
     * status : 1
     * createName : 谢红林
     * createTime : 1539770883000
     * seeCount : 0
     * commentCount : 3
     */

    private String id;
    private boolean isNewRecord;
    private String title;
    private String content;
    private long beginTime;
    private long endTime;
    private String userId;
    private String officeId;
    private String officeName;
    private String status;
    private String createName;
    private long createTime;
    private int seeCount;
    private int commentCount;

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

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getSeeCount() {
        return seeCount;
    }

    public void setSeeCount(int seeCount) {
        this.seeCount = seeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
