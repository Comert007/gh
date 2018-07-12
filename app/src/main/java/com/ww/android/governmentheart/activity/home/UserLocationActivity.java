package com.ww.android.governmentheart.activity.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.location.LocationHelper;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.PermissionItem;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class UserLocationActivity extends BaseActivity<VoidView, MainModel> {

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
                    .startMultiPermission(this, permissionItems,
                            new CustomPermissionCallback() {
                                @Override
                                public void onFinish() {
                                    super.onFinish();
                                    onLocation();
//                                    organizations();
                                }
                            });
        } else {
            onLocation();
//            organizations();
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
        LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                .ic_location_circle);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        baiduMap.addOverlay(option);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
    }

    private void showMap(double latitude, double longitude, boolean isCenter) {
        LatLng point = new LatLng(latitude, longitude);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                .ic_location_circle);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        baiduMap.addOverlay(option);

        if (isCenter){
            LatLng point1 = new LatLng(latitude, longitude);
            BitmapDescriptor bitmap1 = BitmapDescriptorFactory.fromResource(R.mipmap
                    .ic_location_circle);
            OverlayOptions option1 = new MarkerOptions().position(point1).icon(bitmap1);
            baiduMap.addOverlay(option1);
            baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point1));
        }
        // lng: 107.366666666667
        //   lat: 22.4166666666667

    }

    @OnClick({R.id.iv_back, R.id.container_members})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.container_members:
                MemberActivity.start(this);
                break;
        }
    }

    private void organizations() {
        Map map = new HashMap();
        m.organizations(map, new BaseObserver<PageListBean<OrganizationBean>>(this, bindToLifecycle
                ()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OrganizationBean>
                                             organizationBeanPageListBean,
                                     @Nullable List<PageListBean<OrganizationBean>> list, @Nullable
                                             PageBean<PageListBean<OrganizationBean>> pageBean) {

                if (organizationBeanPageListBean != null && organizationBeanPageListBean.getList
                        () != null
                        && organizationBeanPageListBean.getList().size() > 0) {
                    List<OrganizationBean> organizationBeans = organizationBeanPageListBean
                            .getList();
                    boolean isCenter = false;
                    for (int i = 0; i < organizationBeans.size(); i++) {
                        OrganizationBean organizationBean = organizationBeans.get(i);
                        if (i == organizationBeans.size()-1){
                            isCenter = true;
                        }
                        if (!TextUtils.isEmpty(organizationBean.getLatitude()) && !TextUtils
                                .isEmpty(organizationBean.getLongitude())) {
                            double latitude = Double.parseDouble(organizationBean.getLatitude());
                            double longitude = Double.parseDouble(organizationBean.getLongitude());
                            showMap(latitude, longitude, isCenter);
                        }
                    }
                }
            }

        });
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
