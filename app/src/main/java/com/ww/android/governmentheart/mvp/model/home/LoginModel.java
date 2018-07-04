package com.ww.android.governmentheart.mvp.model.home;

import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

public class LoginModel extends BaseModel {

    public void initPass(String phone, BaseObserver<PassBean> observer) {
        HttpRequest.loginApi().initPass(phone)
                .compose(RxSchedulers.cutObservableMain())
                .subscribe(observer);
    }

    public void login(String username, String pass) {

    }
}
