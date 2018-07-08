package com.ww.android.governmentheart.mvp.bean.together;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class ActLeftBean {
    public boolean isSelected;
    public String name;

    public ActLeftBean(String name) {
        this.name = name;
    }

    public ActLeftBean(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
