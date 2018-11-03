package com.ww.android.governmentheart.mvp.bean.work;

public class ReceptionEntity {


    /**
     * id : abfb33d77bef4617a10ffad38dab01e3
     * isNewRecord : false
     * createDate : 2018-10-28 14:44:01
     * updateDate : 2018-10-28 14:44:01
     * title : 测试附件上传
     * summary : 测试附件上传
     * status : 1
     * createUser : {"id":"1","isNewRecord":false,"mobile":"8675","loginName":"system",
     * "name":"系统管理员","phone":"8675","userType":"20","loginFlag":"1",
     * "photo":"/userfiles/1/images/photo/2018/07/682c0d4d18c6d03648d20631d8aa16e6.jpg",
     * "admin":true,"roleNames":""}
     * readNum : 1
     * unReadNum : 4
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String title;
    private String summary;
    private String status;
    private CreateUserBean createUser;
    private String readNum;
    private String unReadNum;

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

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(String unReadNum) {
        this.unReadNum = unReadNum;
    }

    public static class CreateUserBean {
        /**
         * id : 1
         * isNewRecord : false
         * mobile : 8675
         * loginName : system
         * name : 系统管理员
         * phone : 8675
         * userType : 20
         * loginFlag : 1
         * photo : /userfiles/1/images/photo/2018/07/682c0d4d18c6d03648d20631d8aa16e6.jpg
         * admin : true
         * roleNames :
         */

        private String id;
        private boolean isNewRecord;
        private String mobile;
        private String loginName;
        private String name;
        private String phone;
        private String userType;
        private String loginFlag;
        private String photo;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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
