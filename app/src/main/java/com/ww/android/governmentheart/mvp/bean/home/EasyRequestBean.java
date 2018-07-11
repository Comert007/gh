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
    public int num;


    public static class Builder {

        private String id;
        private String type;
        private String name;
        private String url;
        private int num;

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

        public Builder setNum(int num) {
            this.num = num;
            return this;
        }

        public EasyRequestBean build() {
            EasyRequestBean easyRequestBean = new EasyRequestBean();
            easyRequestBean.id = id;
            easyRequestBean.name = name;
            easyRequestBean.type = type;
            easyRequestBean.url = url;
            easyRequestBean.num = num;
            return easyRequestBean;
        }
    }
}
