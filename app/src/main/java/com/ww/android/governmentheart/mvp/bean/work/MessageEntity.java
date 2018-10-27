package com.ww.android.governmentheart.mvp.bean.work;

public class MessageEntity {


    /**
     * id : 1
     * isNewRecord : false
     * createDate : 2018-10-22 00:00:00
     * title : 测试消息
     * content : 测试消息
     * menuCode : 5
     * dataId : 23
     * userId : 99999999999
     * userName : 匿名用户
     * readStatus : 0
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String title;
    private String content;
    private String menuCode;
    private String dataId;
    private String userId;
    private String userName;
    private String readStatus;

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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
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

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }
}
