package com.ww.android.governmentheart.mvp.bean.wisdom;

public class TransmissionUserBean {


    /**
     * id : de4014e64b7f4e029b707e2b78270f03
     * isNewRecord : false
     * createDate : 2018-10-29 10:02:12
     * updateDate : 2018-10-29 10:02:12
     * mId : 6fc713525e5e4af7b5a3cea969449a74
     * user : {"id":"186","isNewRecord":false,"name":"万长明","loginFlag":"1","admin":false,
     * "roleNames":""}
     * title : 测试APP消息和资料的联动
     * status : 1
     * officeName : 民建
     * mid : 6fc713525e5e4af7b5a3cea969449a74
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String mId;
    private UserBean user;
    private String title;
    private String status;
    private String officeName;
    private String mid;

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

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public static class UserBean {
        /**
         * id : 186
         * isNewRecord : false
         * name : 万长明
         * loginFlag : 1
         * admin : false
         * roleNames :
         */

        private String id;
        private boolean isNewRecord;
        private String name;
        private String loginFlag;
        private boolean admin;
        private String roleNames;

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

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }
    }
}
