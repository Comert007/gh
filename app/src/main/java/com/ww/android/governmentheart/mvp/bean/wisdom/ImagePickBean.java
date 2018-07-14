package com.ww.android.governmentheart.mvp.bean.wisdom;

import com.ww.android.governmentheart.mvp.bean.MultipleBean;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ImagePickBean extends MultipleBean {
    public static int MULTIPLE_DEFAULT_IMAGE = 4;
    public static int MULTIPLE_ACTUAL_IMAGE = 5;

    public String path;       //图片的路径

    public String fileId;
    public String suffix;
    public String name;

    public ImagePickBean(int itemType) {
        super(itemType);
    }



}
