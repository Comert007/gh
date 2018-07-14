package com.ww.android.governmentheart.mvp.bean.home;

/**
 * @Author feng
 * @Date 2018/7/14
 */
public class DeputyPersonBean {
    /**
     * id :
     * isNewRecord : true
     * loginFlag : 1
     * roleNames :
     * admin : false
     */

    private String id;
    private boolean isNewRecord;
    private String loginFlag;
    private String roleNames;
    private boolean admin;

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

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
