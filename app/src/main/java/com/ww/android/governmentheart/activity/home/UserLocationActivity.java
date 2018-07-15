package com.ww.android.governmentheart.activity.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.CodeType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.home.MapExtraBean;
import com.ww.android.governmentheart.mvp.bean.home.OfficesBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationDetailBean;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ContactBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.home.UserLocationView;
import com.ww.android.governmentheart.network.BaseObserver;
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
public class UserLocationActivity extends BaseActivity<UserLocationView, MainModel> {

    @BindView(R.id.mmap)
    MapView mapView;

    private BaiduMap baiduMap;
    private HashMap<Integer, Marker> mMarkers;
    private int type = 1;

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
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom
                (14).build()));
        //103.679459,30.636479
        LatLng point = new LatLng(30.636479, 103.679459);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
        initListener();
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
                                    organizations("");
                                }
                            });
        } else {
            organizations("");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == CodeType.REQUEST_CHOOSE_CONTACT && resultCode == CodeType
                .RESULT_CHOOSE_CONTACT) {
            ContactBean contactBean = (ContactBean) data.getSerializableExtra("contact");
            v.setBottomVisible(1);
            v.showInfo(contactBean.getName(), TextUtils.isEmpty(contactBean.getWorkplace()) ?
                    "暂无工作经历" : contactBean.getWorkplace());
        }

        if (data != null && requestCode == CodeType.REQUEST_MEMBER && resultCode == CodeType
                .RESULT_MEMBER) {
            OrganizationTypeBean typeBean = (OrganizationTypeBean) data.getSerializableExtra
                    ("type");
            baiduMap.clear();
            organizations(typeBean.getCode());
        }
    }

    private void initListener() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                if (bundle != null) {
                    MapExtraBean extraBean = (MapExtraBean) bundle.getSerializable("extra");
                    if (type == 1) {
                        if (extraBean != null) {
                            organizationDetail(extraBean);
                        }
                    } else {
                        for (Integer integer : mMarkers.keySet()) {
                            Marker m = mMarkers.get(integer);
                            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                                    .ic_location_circle);
                            m.setIcon(bitmap);
                        }
                        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                                .ic_location_circle_ok);
                        marker.setIcon(bitmap);
                        v.setBottomVisible(1);
                        v.showInfo(extraBean.name,extraBean.description);
                    }
                }
                return false;
            }
        });
    }

    private void showMap(double latitude, double longitude, MapExtraBean extraBean) {
        LatLng point = new LatLng(latitude, longitude);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                .ic_location_circle);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        Marker marker = (Marker) baiduMap.addOverlay(option);
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra", extraBean);
        marker.setExtraInfo(bundle);
        mMarkers.put(extraBean.position, marker);

    }

    private void showText(double latitude,double longitude,String name){
        LatLng point = new LatLng(latitude, longitude);
        //创建InfoWindow展示的view
        Button button = new Button(getApplicationContext());
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setBackgroundResource(R.drawable.shape_gray_border_round_corner_3);
        button.setText(name);

        InfoWindow mInfoWindow = new InfoWindow(button, point, -47);
        baiduMap.showInfoWindow(mInfoWindow);
    }

    @OnClick({R.id.iv_back, R.id.container_members,R.id.tv_member_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.container_members:
                if (type == 1) {
                    MemberActivity.start(this);
                }
                break;
            case R.id.tv_member_close:
                v.setBottomVisible(0);
                break;
        }
    }

    private void organizations(String code) {
        Map map = new HashMap();
        if (!TextUtils.isEmpty(code)) {
            map.put("code", code);
        }
        m.organizations(map, new BaseObserver<PageListBean<OrganizationBean>>(this, bindToLifecycle
                ()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OrganizationBean>
                                             organizationBeanPageListBean,
                                     @Nullable List<PageListBean<OrganizationBean>> list, @Nullable
                                             PageBean<PageListBean<OrganizationBean>> pageBean) {
                type = 1;
                if (organizationBeanPageListBean != null && organizationBeanPageListBean.getList
                        () != null
                        && organizationBeanPageListBean.getList().size() > 0) {
                    v.setBottomVisible(2);
                    v.showOrganizationName("查看统战成员");
                    List<OrganizationBean> organizationBeans = organizationBeanPageListBean
                            .getList();
                    mMarkers = new HashMap<>();
                    for (int i = 0; i < organizationBeans.size(); i++) {
                        OrganizationBean organizationBean = organizationBeans.get(i);
                        if (!TextUtils.isEmpty(organizationBean.getLatitude()) && !TextUtils
                                .isEmpty(organizationBean.getLongitude())) {
                            double latitude = Double.parseDouble(organizationBean.getLatitude());
                            double longitude = Double.parseDouble(organizationBean.getLongitude());
                            MapExtraBean extraBean = new MapExtraBean.Builder()
                                    .setId(organizationBean.getId())
                                    .setPosition(i)
                                    .setName(organizationBean.getName())
                                    .setImage(organizationBean.getImage())
                                    .setDescription(organizationBean.getRemarks()).build();
                            showMap(latitude, longitude, extraBean);
                        }
                    }
                }
            }

        });
    }


    /**
     */
    private void organizationDetail(MapExtraBean extraBean) {
        Map map = new HashMap();
        map.put("id",extraBean.id);
        m.organizationDetail(map, new BaseObserver<PageListBean<OrganizationDetailBean>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OrganizationDetailBean>
                                             organizationDetailBeanPageListBean, @Nullable
                                             List<PageListBean<OrganizationDetailBean>> list,
                                     @Nullable PageBean<PageListBean<OrganizationDetailBean>>
                                             page) {
                if (organizationDetailBeanPageListBean != null &&
                        organizationDetailBeanPageListBean.getData() != null) {
                    OrganizationDetailBean detailBean = organizationDetailBeanPageListBean
                            .getData();

                    List<OfficesBean> officesBeans = detailBean.getOffices();
//                    v.setBottomVisible(2);
                    if (officesBeans != null && officesBeans.size() > 0) {
                        type = 2;
                        baiduMap.clear();
                        mMarkers = new HashMap<>();
                        v.showOrganizationName(detailBean.getName());
                        for (int i = 0; i < officesBeans.size(); i++) {
                            OfficesBean officesBean = officesBeans.get(i);
                            if (!TextUtils.isEmpty(officesBean.getLatitude()) && !TextUtils
                                    .isEmpty(officesBean.getLongitude())) {
                                double latitude = Double.parseDouble(officesBean.getLatitude());
                                double longitude = Double.parseDouble(officesBean.getLongitude());
                                MapExtraBean extraBean = new MapExtraBean.Builder()
                                        .setId(officesBean.getId())
                                        .setPosition(i)
                                        .setName(officesBean.getName())
                                        .setImage(officesBean.getNameImage())
                                        .setDescription(officesBean.getRemarks()).build();
                                showMap(latitude, longitude, extraBean);
                            }
                        }
                    } else {
                        for (Integer integer : mMarkers.keySet()) {
                            Marker m = mMarkers.get(integer);
                            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                                    .ic_location_circle);
                            m.setIcon(bitmap);
                        }

                        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap
                                .ic_location_circle_ok);
                        mMarkers.get(extraBean.position).setIcon(bitmap);
                        v.setBottomVisible(1);
                        v.showInfo(extraBean.name, extraBean.description);
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
