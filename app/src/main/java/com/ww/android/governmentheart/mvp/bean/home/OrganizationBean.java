package com.ww.android.governmentheart.mvp.bean.home;

import com.ww.android.governmentheart.mvp.bean.MultipleBean;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class OrganizationBean extends MultipleBean{

    public static int MULTIPLE_TITLE = 4;
    /**
     * id : 47f1e872f2c34a72806d192675c053ba
     * isNewRecord : false
     * remarks :
     * createDate : 2018-07-04 21:06:32
     * updateDate : 2018-07-07 21:36:50
     * parentIds : 0,1,95dfdabce6a64981a025eda734ec041a,
     * name : 崇州市民族宗教事务局
     * sort : 30
     * area : {"id":"d96114e959d6405da4c9b04e3a5f24c8","isNewRecord":false,"parentIds":"0,1,
     * 9157dae280e14b3c9878fde4f7aa9cd1,5e786a5ff0d94d518a1b27369f29de5c,","name":"崇州市",
     * "sort":30,"parentId":"0"}
     * code : 100000005005
     * type : 3
     * grade : 1
     * address :
     * zipCode :
     * master :
     * phone :
     * fax :
     * email :
     * longitude : 103.637329
     * latitude : 30.584855
     * useable : 1
     * primaryPerson : {"id":"","isNewRecord":true,"loginFlag":"1","roleNames":"","admin":false}
     * deputyPerson : {"id":"","isNewRecord":true,"loginFlag":"1","roleNames":"","admin":false}
     * orgFlag : 220000
     * orgFlagId : 9174ecdbe09e462da48ccd5400d59510
     * orgFlagName : 民宗局
     * parentId : 95dfdabce6a64981a025eda734ec041a
     */

    private String id;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String parentIds;
    private String name;
    private int sort;
    private AreaBean area;
    private String code;
    private String type;
    private String grade;
    private String address;
    private String zipCode;
    private String master;
    private String phone;
    private String fax;
    private String email;
    private String longitude;
    private String latitude;
    private String useable;
    private PrimaryPersonBean primaryPerson;
    private DeputyPersonBean deputyPerson;
    private String orgFlag;
    private String orgFlagId;
    private String orgFlagName;
    private String parentId;


    public OrganizationBean(int itemType) {
        super(itemType);
    }

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

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public PrimaryPersonBean getPrimaryPerson() {
        return primaryPerson;
    }

    public void setPrimaryPerson(PrimaryPersonBean primaryPerson) {
        this.primaryPerson = primaryPerson;
    }

    public DeputyPersonBean getDeputyPerson() {
        return deputyPerson;
    }

    public void setDeputyPerson(DeputyPersonBean deputyPerson) {
        this.deputyPerson = deputyPerson;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    public String getOrgFlagId() {
        return orgFlagId;
    }

    public void setOrgFlagId(String orgFlagId) {
        this.orgFlagId = orgFlagId;
    }

    public String getOrgFlagName() {
        return orgFlagName;
    }

    public void setOrgFlagName(String orgFlagName) {
        this.orgFlagName = orgFlagName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public static class AreaBean {
        /**
         * id : d96114e959d6405da4c9b04e3a5f24c8
         * isNewRecord : false
         * parentIds : 0,1,9157dae280e14b3c9878fde4f7aa9cd1,5e786a5ff0d94d518a1b27369f29de5c,
         * name : 崇州市
         * sort : 30
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private String parentIds;
        private String name;
        private int sort;
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

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public static class PrimaryPersonBean {
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

    public static class DeputyPersonBean {
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
}
