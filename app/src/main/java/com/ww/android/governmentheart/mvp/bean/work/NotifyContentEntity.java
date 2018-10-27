package com.ww.android.governmentheart.mvp.bean.work;

public class NotifyContentEntity {


    /**
     * id : c4bede6e8c304262b8ed2f4c7d1ab8ed
     * isNewRecord : false
     * oaNotify : {"id":"b981a7cac3254db5b268e336a8c73d1c","isNewRecord":false,"self":false,
     * "oaNotifyRecordNames":"","oaNotifyRecordIds":""}
     * user : {"id":"1","isNewRecord":false,"name":"系统管理员","loginFlag":"1","roleNames":"",
     * "admin":true}
     * readFlag : 1
     * readDate : 1539878400000
     */

    private String id;
    private boolean isNewRecord;
    private OaNotifyBean oaNotify;
    private UserBean user;
    private String readFlag;
    private long readDate;

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

    public OaNotifyBean getOaNotify() {
        return oaNotify;
    }

    public void setOaNotify(OaNotifyBean oaNotify) {
        this.oaNotify = oaNotify;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public long getReadDate() {
        return readDate;
    }

    public void setReadDate(long readDate) {
        this.readDate = readDate;
    }

    public static class OaNotifyBean {
        /**
         * id : b981a7cac3254db5b268e336a8c73d1c
         * isNewRecord : false
         * self : false
         * oaNotifyRecordNames :
         * oaNotifyRecordIds :
         */

        private String id;
        private boolean isNewRecord;
        private boolean self;
        private String oaNotifyRecordNames;
        private String oaNotifyRecordIds;

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

        public boolean isSelf() {
            return self;
        }

        public void setSelf(boolean self) {
            this.self = self;
        }

        public String getOaNotifyRecordNames() {
            return oaNotifyRecordNames;
        }

        public void setOaNotifyRecordNames(String oaNotifyRecordNames) {
            this.oaNotifyRecordNames = oaNotifyRecordNames;
        }

        public String getOaNotifyRecordIds() {
            return oaNotifyRecordIds;
        }

        public void setOaNotifyRecordIds(String oaNotifyRecordIds) {
            this.oaNotifyRecordIds = oaNotifyRecordIds;
        }
    }

    public static class UserBean {
        /**
         * id : 1
         * isNewRecord : false
         * name : 系统管理员
         * loginFlag : 1
         * roleNames :
         * admin : true
         */

        private String id;
        private boolean isNewRecord;
        private String name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
}
