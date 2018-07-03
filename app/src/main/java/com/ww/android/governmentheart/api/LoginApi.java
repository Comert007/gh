package com.ww.android.governmentheart.api;

import com.ww.android.governmentheart.mvp.bean.ResponseBean;

import rx.Observable;
import ww.com.http.core.AjaxParams;

public class LoginApi extends BaseApi {

    public static Observable<ResponseBean> login(String url, String phone, String password){
       return onPost(url,new AjaxParams());
    }

}
