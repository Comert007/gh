package com.ww.android.governmentheart.mvp.bean.login;

import java.io.Serializable;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public class UserInfoBean implements Serializable{


    /**
     * loginName : 13880819901
     * no : 100
     * name : 陈红霞
     * email :
     * phone : 13880819901
     * mobile : 13880819901
     * loginIp : 1
     * photo :
     * id : 100
     */

    private String loginName;
    private String no;
    private String name;
    private String email;
    private String phone;
    private String mobile;
    private String loginIp;
    private String photo;
    private String id;

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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
