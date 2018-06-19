package com.ww.android.governmentheart.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.style.FarmFragment;
import com.ww.android.governmentheart.fragment.style.FeaturesFragment;
import com.ww.android.governmentheart.fragment.style.InterviewsFragment;
import com.ww.android.governmentheart.fragment.style.IntroduceFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class StyleFragment extends BaseFragment<MagicIndicatorView,VoidModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_style;
    }

    @Override
    protected void init() {
        initViewPager();
        v.setTitles(Arrays.asList(getResources().getStringArray(R.array.style_text)));
        v.initMagicIndicator(true);
    }

    /**
     * init viewpager
     */
    private void initViewPager(){
        fragmentManager = getChildFragmentManager();
        addFragment();
        pagerAdapter = new IndicatorPagerAdapter(fragmentManager, fragments);
        v.viewPager.setAdapter(pagerAdapter);
        v.viewPager.setOffscreenPageLimit(4);
    }

    /**
     * 添加fragment
     */
    private void addFragment(){
        if (fragments==null){
            fragments = new ArrayList<>();
        }
        fragments.add(new FeaturesFragment());
        fragments.add(new FarmFragment());
        fragments.add(new IntroduceFragment());
        fragments.add(new InterviewsFragment());
    }
}