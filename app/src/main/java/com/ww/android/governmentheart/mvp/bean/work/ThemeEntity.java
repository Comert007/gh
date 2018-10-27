package com.ww.android.governmentheart.mvp.bean.work;

public class ThemeEntity {


    /**
     * id : 1
     * isNewRecord : false
     * title : 你吃了吗
     * content : 话题内容
     * createTime : 1539770883000
     * seeCount : 0
     * commentCount : 3
     */

    private String id;
    private boolean isNewRecord;
    private String title;
    private String content;
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
