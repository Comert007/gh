package com.ww.android.governmentheart.mvp.bean.event;

public class UpdateAppEvent {

    public UpdateAppEvent(String url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
