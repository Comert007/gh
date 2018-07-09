package com.ww.android.governmentheart.utils.permission;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.ww.android.governmentheart.R;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class PermissionHelper {

    public static void startSinglePermission(Context context, PermissionItem permissionItem,
                                             PermissionCallback permissionCallback) {
        List<PermissionItem> permissions = new ArrayList<>();
        permissions.add(permissionItem);
        startMultiPermission(context, permissions,permissionCallback);
    }

    public static void startMultiPermission(Context context, List<PermissionItem> permissions,
                                            PermissionCallback permissionCallback) {
        HiPermission.create(context)
                .title("权限申请")
                .permissions(permissions)
                .msg(context.getResources().getString(R.string.permission_msg))
                .style(R.style.PermissionRedStyle)
                .filterColor(ResourcesCompat.getColor(context.getResources(), R.color
                        .colorPrimary, context.getTheme()))//permission icon color
                .checkMutiPermission(permissionCallback);
    }
}
