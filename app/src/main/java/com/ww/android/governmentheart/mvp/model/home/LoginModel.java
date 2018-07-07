package com.ww.android.governmentheart.mvp.model.home;

import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.HashMap;
import java.util.Map;

public class LoginModel extends BaseModel {

    /**
     * 获取初始密码
     * @param phone
     * @param observer
     */
    public void initPass(String phone, BaseObserver<PassBean> observer) {
        Map map = new HashMap();
        map.put("phone",phone);
        HttpRequest.loginApi().initPass(JsonParse.crateMapForm(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);

    }

    /**
     * 登录
     * @param username
     * @param pass
     * @param observer
     */
    public void login(String username, String pass,BaseObserver<UserBean> observer) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("pass",pass);
        HttpRequest.loginApi().login(JsonParse.crateMapForm(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

}
