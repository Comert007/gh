package com.ww.android.governmentheart.mvp.bean.home;

/**
 * @author feng
 * @Date 2018/7/10.
 */
public class CommentBean {


    /**
     * id : ae8ddd2bb59146e289707aa68a88b786
     * isNewRecord : false
     * createDate : 2018-07-22 17:02:00
     * category : {"id":"33d35cb4562a4ea8bea9452e617ea689","isNewRecord":false,"sort":30,
     * "module":"","inMenu":"0","inList":"1","showModes":"0","allowComment":"0","isAudit":"0",
     * "url":"/f/list-33d35cb4562a4ea8bea9452e617ea689.html","root":false,
     * "ids":"33d35cb4562a4ea8bea9452e617ea689","parentId":"0"}
     * contentId : ef70c3ddbc364261a0ecdc42edbd32a7
     * title : 回复v就叫姐姐
     * content : 回复v就叫姐姐
     * user : {"id":"361","isNewRecord":false,"remarks":"分管领导","createDate":"2018-07-08
     * 10:13:53","updateDate":"2018-07-08 10:13:53","loginName":"13880819901","no":"1261",
     * "name":"陈红霞","email":"","phone":"13880819901","mobile":"13880819901","userType":"20",
     * "loginIp":"","loginFlag":"","photo":"http://tz.yizykj.com","oldLoginIp":"","admin":false,
     * "roleNames":""}
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private CategoryBean category;
    private String contentId;
    private String title;
    private String content;
    private UserBean user;

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

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class CategoryBean {
        /**
         * id : 33d35cb4562a4ea8bea9452e617ea689
         * isNewRecord : false
         * sort : 30
         * module :
         * inMenu : 0
         * inList : 1
         * showModes : 0
         * allowComment : 0
         * isAudit : 0
         * url : /f/list-33d35cb4562a4ea8bea9452e617ea689.html
         * root : false
         * ids : 33d35cb4562a4ea8bea9452e617ea689
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private int sort;
        private String module;
        private String inMenu;
        private String inList;
        private String showModes;
        private String allowComment;
        private String isAudit;
        private String url;
        private boolean root;
        private String ids;
        private String parentId;

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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getInMenu() {
            return inMenu;
        }

        public void setInMenu(String inMenu) {
            this.inMenu = inMenu;
        }

        public String getInList() {
            return inList;
        }

        public void setInList(String inList) {
            this.inList = inList;
        }

        public String getShowModes() {
            return showModes;
        }

        public void setShowModes(String showModes) {
            this.showModes = showModes;
        }

        public String getAllowComment() {
            return allowComment;
        }

        public void setAllowComment(String allowComment) {
            this.allowComment = allowComment;
        }

        public String getIsAudit() {
            return isAudit;
        }

        public void setIsAudit(String isAudit) {
            this.isAudit = isAudit;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isRoot() {
            return root;
        }

        public void setRoot(boolean root) {
            this.root = root;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public static class UserBean {
        /**
         * id : 361
         * isNewRecord : false
         * remarks : 分管领导
         * createDate : 2018-07-08 10:13:53
         * updateDate : 2018-07-08 10:13:53
         * loginName : 13880819901
         * no : 1261
         * name : 陈红霞
         * email :
         * phone : 13880819901
         * mobile : 13880819901
         * userType : 20
         * loginIp :
         * loginFlag :
         * photo : http://tz.yizykj.com
         * oldLoginIp :
         * admin : false
         * roleNames :
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;
        private String loginName;
        private String no;
        private String name;
        private String email;
        private String phone;
        private String mobile;
        private String userType;
        private String loginIp;
        private String loginFlag;
        private String photo;
        private String oldLoginIp;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
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

        public String getOldLoginIp() {
            return oldLoginIp;
        }

        public void setOldLoginIp(String oldLoginIp) {
            this.oldLoginIp = oldLoginIp;
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
