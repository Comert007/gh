package com.ww.android.governmentheart.mvp.bean;

import java.io.Serializable;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class MultipleBean implements Serializable {

    public static int MULTIPLE_HEADER = 1;
    public static int MULTIPLE_BODY = 2;
    public static int MULTIPLE_BOTTOM = 3;
    public static int MULTIPLE_FAILURE = 0;


    public MultipleBean(int itemType) {
        this.itemType = itemType;
    }

    private int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
