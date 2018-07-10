package com.ww.android.governmentheart.mvp.bean.home;

import java.io.Serializable;

/**
 * @author feng
 * @Date 2018/7/10.
 */
public class EasyRequestBean implements Serializable {

    public String id;
    public String type;
    public String name;
    public String url;


    public static class Builder {

        private String id;
        private String type;
        private String name;
        private String url;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public EasyRequestBean build() {
            EasyRequestBean easyRequestBean = new EasyRequestBean();
            easyRequestBean.id = id;
            easyRequestBean.name = name;
            easyRequestBean.type = type;
            easyRequestBean.url = url;
            return easyRequestBean;
        }
    }
}
