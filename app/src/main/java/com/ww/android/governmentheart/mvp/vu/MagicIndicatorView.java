package com.ww.android.governmentheart.mvp.vu;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ww.android.governmentheart.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles
        .ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import ww.com.core.Debug;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class MagicIndicatorView implements IView {
    private CommonNavigator commonNavigator;
    private CommonNavigatorAdapter navigatorAdapter;

    private List<String> titles;
    private Activity activity;

    @Nullable
    @BindView(R.id.magic_indicator)
    public MagicIndicator magicIndicator;
    @Nullable
    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    @BindColor(R.color.color_indicator)
    int colorIndicator;
    @BindColor(R.color.color_black)
    int colorBlack;

    @Override
    public void onAttach(@NonNull Activity preActivity, @NonNull View contentView) {
        this.activity = preActivity;
        ButterKnife.bind(this, contentView);
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        if (this.titles == null){
            this.titles = new ArrayList<>();
        }
    }

    public void initMagicIndicator(boolean adjustMode){
        if (activity == null){
            Debug.d("the activity is null");
            return;
        }
        commonNavigator = new CommonNavigator(activity);
        commonNavigator.setAdjustMode(adjustMode);
        navigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles==null?0:titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(colorIndicator);
                colorTransitionPagerTitleView.setTextSize(15);
                colorTransitionPagerTitleView.setSelectedColor(colorBlack);
                colorTransitionPagerTitleView.setText(titles.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (viewPager!=null){
                            viewPager.setCurrentItem(index);
                        }
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(colorIndicator);
                indicator.setLineHeight(1);
                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                return indicator;
            }
        };

        commonNavigator.setAdapter(navigatorAdapter);
        if (magicIndicator!=null){
            magicIndicator.setNavigator(commonNavigator);
        }
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
