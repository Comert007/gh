package com.ww.android.governmentheart.mvp.bean.wisdom;

/**
 * @Author feng
 * @Date 2018/7/11
 */
public class UploadBean {
    /**
     * fileid：‘xxx’,name:’文件名’,suffix：‘后缀’，path:’路径’,size’大小’
     */

    private String id;
    private String fileid;
    private String name;
    private String suffix;
    private String path;
    private String size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
