package com.ww.android.governmentheart.mvp.bean.login;

import com.ww.android.governmentheart.mvp.manager.IUserInfo;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public class UserBean implements IUserInfo{

    private String token;
    private UserInfoBean user;

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoBean getUser() {
        return user;
    }

    public void setUser(UserInfoBean user) {
        this.user = user;
    }
}
