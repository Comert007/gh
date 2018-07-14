package com.ww.android.governmentheart.mvp.bean.home;

import java.util.List;

/**
 * @Author feng
 * @Date 2018/7/14
 */
public class OfficesBean {
    /**
     * id : 0c5384cc5697401db93adf9180f4051c
     * isNewRecord : false
     * remarks : 祖灵寺乃古刹丛林，在莹华山距怀远镇西8里。始建于乾隆47年（1782
     * 年）。地处川西盆沿，坐落于层峦叠嶂之九峰山麓，背负皑皑雪岭，岷山莽莽；西来面临锦绣天府川原，浩浩车驰；北继青城天下幽之灵脉；南接峨嵋天下秀之佛光，钟灵毓秀，孕贤启慧。林幽寺静，常有上人礼禅，晨钟暮鼓，诚为修行悟道之小天竺，慈航普渡之宝筏。党的十一届三中全会以后，佛事活动纳入正常。在各级政府关怀帮助下修复祖灵寺，使梵宇重辉，成一方净土。
     * createDate : 2018-07-08 15:08:53
     * updateDate : 2018-07-13 14:03:27
     * parentIds : 0,1,95dfdabce6a64981a025eda734ec041a,47f1e872f2c34a72806d192675c053ba,
     * name : 祖灵寺
     * sort : 30
     * area : {"id":"d96114e959d6405da4c9b04e3a5f24c8","isNewRecord":false,"parentIds":"0,1,
     * 9157dae280e14b3c9878fde4f7aa9cd1,5e786a5ff0d94d518a1b27369f29de5c,","name":"崇州市",
     * "sort":30,"parentId":"0"}
     * code : 100000005005014
     * type : 3
     * grade : 1
     * address :
     * zipCode :
     * phone :
     * fax :
     * email :
     * longitude : 103.53487
     * latitude : 30.73992
     * useable : 1
     * nameImage : /userfiles/1/_thumbs/images/cms/article/2018/07/%E7%A5%96%E7%81%B5%E5%AF
     * %BA.png
     * persons : [{"id":"380","isNewRecord":false,"remarks":"","createDate":"2018-07-08
     * 10:13:53","updateDate":"2018-07-08 10:13:53","loginName":"13666208061","no":"1280",
     * "name":"释觉彦","email":"","phone":"13666208061","mobile":"13666208061","userType":"10",
     * "loginIp":"","loginFlag":"","photo":"","oldLoginIp":"","nativeplace":"成都",
     * "birthday":"1969.02","degree":"3","workplace":"祖灵寺常驻6年","homeaddress":"怀远镇华严村",
     * "speciality":"书法、画画","sex":"1","nation":"1","roleNames":"","admin":false}]
     * parentId : 47f1e872f2c34a72806d192675c053ba
     * orgFlag :
     * master :
     * primaryPerson : {"id":"","isNewRecord":true,"loginFlag":"1","roleNames":"","admin":false}
     * deputyPerson : {"id":"","isNewRecord":true,"loginFlag":"1","roleNames":"","admin":false}
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
    private String phone;
    private String fax;
    private String email;
    private String longitude;
    private String latitude;
    private String useable;
    private String nameImage;
    private String parentId;
    private String orgFlag;
    private String master;
    private PrimaryPersonBean primaryPerson;
    private DeputyPersonBean deputyPerson;
    private List<PersonBean> persons;

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

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
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

    public List<PersonBean> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonBean> persons) {
        this.persons = persons;
    }



}
