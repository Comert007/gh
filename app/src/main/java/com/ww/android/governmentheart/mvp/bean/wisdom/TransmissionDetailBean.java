package com.ww.android.governmentheart.mvp.bean.wisdom;

import java.util.List;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class TransmissionDetailBean {


    private List<RequestUserBean> users;
    private List<ImagePickBean> files;
    /**
     * id : 9ce2869e95a7443ead7a5611f3672563
     * isNewRecord : false
     * createDate : 2018-07-14 17:07:36
     * updateDate : 2018-07-14 17:07:36
     * title : 添加资料发送
     * summary : 添加资料发送
     * status : 1
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String title;
    private String summary;
    private String status;


    public List<RequestUserBean> getUsers() {
        return users;
    }

    public void setUsers(List<RequestUserBean> users) {
        this.users = users;
    }

    public List<ImagePickBean> getFiles() {
        return files;
    }

    public void setFiles(List<ImagePickBean> files) {
        this.files = files;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
