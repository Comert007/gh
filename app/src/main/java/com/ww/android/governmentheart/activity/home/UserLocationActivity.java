package com.ww.android.governmentheart.activity.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.utils.location.LocationHelper;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.PermissionItem;
import ww.com.core.Debug;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class UserLocationActivity extends BaseActivity<VoidView, VoidModel> {

    @BindView(R.id.mmap)
    MapView mapView;

    private BaiduMap baiduMap;
    private MyLocationConfiguration.LocationMode locationMode;

    int accuracyCircleFillColor = 0xAAFFFF88;//自定义精度圈填充颜色
    int accuracyCircleStrokeColor = 0xAA00FF00;//自定义精度圈边框颜色

    public static void launch(Context context) {
        Intent starter = new Intent(context, UserLocationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user_location;
    }

    @Override
    protected void beforeOnCreate() {
        SDKInitializer.initialize(getApplicationContext());
    }

    @Override
    protected void init() {
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            List<PermissionItem> permissionItems = new ArrayList<>();
            permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    "存储", R.drawable.permission_ic_storage));
            permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION,
                    "位置", R.drawable.permission_ic_location));
            PermissionHelper
                    .startMultiPermission(this,permissionItems,
                    new CustomPermissionCallback() {
                        @Override
                        public void onFinish() {
                            super.onFinish();
                            onLocation();
                        }
                    });
        } else {
            onLocation();
        }

    }

    private void onLocation() {
        LocationHelper helper = LocationHelper.getInstance(this);
        helper.setLocationListener(new com.ww.android.governmentheart.utils.location
                .LocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                showMap(location);
                helper.stop();
            }
        });

        helper.start();
    }

    private void showMap(BDLocation location) {
        Debug.d("location:" + location.getLongitude() + ",  " + location.getLatitude());
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap
                .ic_location_circle);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration
                .LocationMode.NORMAL, true, descriptor);
        baiduMap.setMyLocationConfiguration(config);

        MyLocationData locData = new MyLocationData.Builder().latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        // 设置定位数据
        baiduMap.setMyLocationData(locData);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

}
