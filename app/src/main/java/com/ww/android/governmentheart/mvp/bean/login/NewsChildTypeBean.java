package com.ww.android.governmentheart.mvp.bean.login;

import android.support.annotation.Nullable;

import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import java.util.List;

/**
 * @author feng
 * @Date 2018/7/16.
 */
public class NewsChildTypeBean extends MultipleBean{

    /**
     * id : 86ab07fc4f314a8fb3e3cd3b6eab067b
     * name : 崇州市商务和投资促进局
     * parentId : 5a3101ada89b4d1c9c6cfb2f32e22e0f
     * image :
     * count : 4
     */

    private String id;
    private String name;
    private String parentId;
    private String image;
    private String count;
    private String description;


    //custom params
    @Nullable
    private List<NewsBean> mNewsBeans;
    private String url;
    private String viewNum;
    private String commentNum;

    public NewsChildTypeBean(int itemType, String id, String name, String parentId, String image,
                             String count, String description, String
                                     url, String viewNum,String commentNum) {
        super(itemType);
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.image = image;
        this.count = count;
        this.description = description;
        this.url = url;
        this.viewNum = viewNum;
        this.commentNum = commentNum;
    }

    public NewsChildTypeBean(int itemType) {
        super(itemType);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Nullable
    public List<NewsBean> getNewsBeans() {
        return mNewsBeans;
    }

    public void setNewsBeans(@Nullable List<NewsBean> newsBeans) {
        mNewsBeans = newsBeans;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
}
