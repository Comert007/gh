package com.ww.android.governmentheart.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import ww.com.core.adapter.MenuTabAdapter;

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
    private HeartFragment mHeartFragment;

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




}
