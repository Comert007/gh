package com.ww.android.governmentheart.mvp.bean.together;

import com.ww.android.governmentheart.mvp.bean.MultipleBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class OrganizationTypeBean extends MultipleBean {

    public static int MULTIPLE_TITLE = 4;
    /**
     * id : aca4c3642fe1476f9b5898694acbcec2
     * code : 22012
     * name : 知联会
     */

    private String id;
    private String code;
    private String name;
    private String image;
    private String userCount;

    private List<OrganizationTypeBean> mOrganizationTypeBeans;

    public OrganizationTypeBean(int itemType) {
        super(itemType);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrganizationTypeBeans(List<OrganizationTypeBean> organizationTypeBeans) {
        mOrganizationTypeBeans = organizationTypeBeans;
    }

    public List<OrganizationTypeBean> getOrganizationTypeBeans() {
        return mOrganizationTypeBeans;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }
}
