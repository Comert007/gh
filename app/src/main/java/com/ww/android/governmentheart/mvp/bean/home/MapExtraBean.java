package com.ww.android.governmentheart.mvp.bean.home;

import java.io.Serializable;

/**
 * @Author feng
 * @Date 2018/7/14
 */
public class MapExtraBean implements Serializable{

    public String id;
    public int position;
    public String name;
    public String description;
    public String image;

    public MapExtraBean(String id, int position, String name, String description, String image) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public static class Builder {
        private String id;
        private int position;
        private String name;
        private String description;
        private String image;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public MapExtraBean build(){
            return new MapExtraBean(id,position,name,description,image);
        }
    }
}
