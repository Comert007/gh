package com.ww.android.governmentheart.mvp.bean.work;

import java.util.List;

public class NotifyEntity {


    /**
     * id : 2a6eb692ee29415d943e915bbb305dd8
     * isNewRecord : false
     * createDate : 2018-10-19 10:51:52
     * updateDate : 2018-10-19 10:51:52
     * type : 0
     * title : 崇州市商务和投资促进局 测试文章1
     * content : 测试通知
     * status : 1
     * readNum : 0
     * unReadNum : 1
     * readFlag : 0
     * self : false
     * oaNotifyRecordIds :
     * oaNotifyRecordNames :
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String type;
    private String title;
    private String content;
    private String status;
    private int readNum;
    private int unReadNum;
    private String readFlag;
    private boolean self;
    private String oaNotifyRecordIds;
    private String oaNotifyRecordNames;
    private List<NotifyContentEntity> oaNotifyRecordList;
    private List<AnnexEntity>attList;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(int unReadNum) {
        this.unReadNum = unReadNum;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getOaNotifyRecordIds() {
        return oaNotifyRecordIds;
    }

    public void setOaNotifyRecordIds(String oaNotifyRecordIds) {
        this.oaNotifyRecordIds = oaNotifyRecordIds;
    }

    public String getOaNotifyRecordNames() {
        return oaNotifyRecordNames;
    }

    public void setOaNotifyRecordNames(String oaNotifyRecordNames) {
        this.oaNotifyRecordNames = oaNotifyRecordNames;
    }

    public List<NotifyContentEntity> getOaNotifyRecordList() {
        return oaNotifyRecordList;
    }

    public void setOaNotifyRecordList(List<NotifyContentEntity> oaNotifyRecordList) {
        this.oaNotifyRecordList = oaNotifyRecordList;
    }

    public List<AnnexEntity> getAttList() {
        return attList;
    }

    public void setAttList(List<AnnexEntity> attList) {
        this.attList = attList;
    }
}
