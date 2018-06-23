package com.ww.android.governmentheart.utils.permission;

import com.ww.android.governmentheart.utils.ToastUtils;

import me.weyye.hipermission.PermissionCallback;
import ww.com.core.Debug;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class CustomPermissionCallback implements PermissionCallback {

    @Override
    public void onClose() {
        Debug.d("onClose");
        ToastUtils.showToast("用户取消");
    }

    @Override
    public void onFinish() {
        Debug.d("onFinish");
    }

    @Override
    public void onDeny(String permission, int position) {
        Debug.d("onDeny");
    }

    @Override
    public void onGuarantee(String permission, int position) {
        Debug.d("onGuarantee");
    }

}
