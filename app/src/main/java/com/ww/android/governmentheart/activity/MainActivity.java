package com.ww.android.governmentheart.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.fragment.HeartFragment;
import com.ww.android.governmentheart.fragment.HomeFragment;
import com.ww.android.governmentheart.fragment.StyleFragment;
import com.ww.android.governmentheart.fragment.TogetherFragment;
import com.ww.android.governmentheart.fragment.WisdomFragment;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.VersionBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.utils.DownloadUtils;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.DialogUtils;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindViews;
import me.weyye.hipermission.PermissionItem;
import ww.com.core.adapter.MenuTabAdapter;
import ww.com.core.utils.PhoneUtils;

public class MainActivity extends BaseActivity<VoidView, MainModel> {

    @BindViews({R.id.tab_home_layout, R.id.tab_heart_layout, R.id.tab_together_layout, R.id
            .tab_style_layout, R.id
            .tab_wisdom_layout})
    List<View> menus;
    @BindViews({R.id.tab_home_image, R.id.tab_heart_image, R.id.tab_together_image, R.id
            .tab_style_image, R.id
            .tab_wisdom_image})
    List<View> images;
    @BindViews({R.id.tab_home_text, R.id.tab_heart_text, R.id.tab_together_text, R.id
            .tab_style_text, R.id
            .tab_wisdom_text})
    List<View> texts;

    private MenuTabAdapter adapter;
    private List<Fragment> fragments;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        addFragments();
        onUpdate();
    }

    private void addFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HeartFragment());
        fragments.add(new TogetherFragment());
        fragments.add(new StyleFragment());
        fragments.add(new WisdomFragment());
        addCheck();

        changeStatus(0);
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }

    private void addCheck() {
        adapter = new MenuTabAdapter(this, menus, fragments, R.id.main_content);
        adapter.setOnMenuClickListener(new MenuTabAdapter.OnMenuClickListener() {
            @Override
            public void onDoubleClick(View view) {

            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tab_home_layout:
                        changeStatus(0);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_heart_layout:
                        changeStatus(1);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_together_layout:
                        changeStatus(2);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_style_layout:
                        changeStatus(3);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_wisdom_layout:
                        changeStatus(4);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                }
            }
        });

    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }

    private void changeStatus(int index) {
        adapter.changeMenuStatus(index);
        changeMenuStatus(index);
        adapter.changeMenu(index);
    }


    public void changeMenuStatus(int index) {
        int imageSize = images.size();
        for (int i = 0; i < imageSize; i++) {
            if (i == index) {
                this.images.get(i).setSelected(true);
                this.texts.get(i).setSelected(true);
            } else {
                this.images.get(i).setSelected(false);
                this.texts.get(i).setSelected(false);
            }
        }
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                BaseApplication.getInstance().clearTopTask(MainActivity.this);
                onBackPressed();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 判断是否有更新
     */
    private void onUpdate(){
        m.onUpdate(new HashMap(), new BaseObserver<VersionBean>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable VersionBean versionBean, @Nullable List<VersionBean> list, @Nullable PageBean<VersionBean> page) {
                String ver =PhoneUtils.getAppVer(MainActivity.this);
                String version = versionBean.getVersion();
                if (version.compareTo(ver) >0){
                    showDialog(versionBean.getDonwLoadUrl());
                }
            }
        });
    }

    /**
     * 显示更新提示
     */
    private void showDialog(String url){
        DialogUtils.showDialog(MainActivity.this, "更新提示", "有最新版本，是否进行下载更新？",
                "确定", (dialogInterface, i) -> {dialogInterface.dismiss();
                update(url);
        });
    }


    private void update(String url){
        final String[] onUrl = {url};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionHelper.startSinglePermission(this, new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R
                    .drawable.permission_ic_storage),new CustomPermissionCallback(){
                @Override
                public void onFinish() {
                    super.onFinish();
                    onUrl[0] = "https://alissl.ucdl.pp.uc.cn/fs01/union_pack/Wandoujia_363645_web_seo_google_homepage.apk";
                    download(onUrl[0]);
                }
            });
        }else {
            download(url);
        }

    }

    private void download(String downloadUrl){
        DownloadUtils dialogUtils = new DownloadUtils(MainActivity.this);
        String name = downloadUrl.substring(downloadUrl.lastIndexOf("/")+1,downloadUrl.length());
        Log.e("DOWN",name);
        dialogUtils.downloadAPK(downloadUrl,name);
    }

}
