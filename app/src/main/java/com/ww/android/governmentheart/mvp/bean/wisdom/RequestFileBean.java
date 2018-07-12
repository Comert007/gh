package com.ww.android.governmentheart.mvp.bean.wisdom;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class RequestFileBean {
    public String path;
    public  String mimeType;


    public RequestFileBean() {
    }

    public RequestFileBean(String path, String mimeType) {
        this.path = path;
        this.mimeType = mimeType;
    }
}
