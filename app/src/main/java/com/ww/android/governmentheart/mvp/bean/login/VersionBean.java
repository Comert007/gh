package com.ww.android.governmentheart.mvp.bean.login;

public class VersionBean {

    /**
     * id : 6db5c99d896d42a7bac5bdd07ce30088
     * isNewRecord : false
     * updateDate : 2018-09-05 16:58:16
     * version : 1.0.2
     * donwLoadUrl : http://tz.yizykj.com/userfiles/1/files/cms/article/2018/09/nginx-1_14_0.zip
     * content : 测试更新
     */

    private String id;
    private boolean isNewRecord;
    private String updateDate;
    private String version;
    private String donwLoadUrl;
    private String content;

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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDonwLoadUrl() {
        return donwLoadUrl;
    }

    public void setDonwLoadUrl(String donwLoadUrl) {
        this.donwLoadUrl = donwLoadUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
