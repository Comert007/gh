package com.ww.android.governmentheart.mvp.bean.wisdom;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class RequestUserBean {
    private String id;
    private String name;
    private String t;

    public RequestUserBean(String id, String name, String t) {
        this.id = id;
        this.name = name;
        this.t = t;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
