package com.ww.android.governmentheart.mvp.bean.wisdom;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class TransmissionBean {


    /**
     * id : 19704eab2915410cb1e475c6e748fc24
     * isNewRecord : false
     * createDate : 2018-07-12 17:28:01
     * updateDate : 2018-07-12 17:28:01
     * title : 资料发送资料发生
     * summary : 资料发送资料发生
     * status : 1
     * createUser : {"id":"361","isNewRecord":false,"loginName":"13880819901","name":"陈红霞",
     * "phone":"13880819901","mobile":"13880819901","userType":"20","loginFlag":"1","photo":"",
     * "roleNames":"","admin":false}
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String title;
    private String summary;
    private String status;
    private CreateUserBean createUser;

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

    public CreateUserBean getCreateUser() {
        return createUser;
    }

    public void setCreateUser(CreateUserBean createUser) {
        this.createUser = createUser;
    }

    public static class CreateUserBean {
        /**
         * id : 361
         * isNewRecord : false
         * loginName : 13880819901
         * name : 陈红霞
         * phone : 13880819901
         * mobile : 13880819901
         * userType : 20
         * loginFlag : 1
         * photo :
         * roleNames :
         * admin : false
         */

        private String id;
        private boolean isNewRecord;
        private String loginName;
        private String name;
        private String phone;
        private String mobile;
        private String userType;
        private String loginFlag;
        private String photo;
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

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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
