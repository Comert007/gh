package com.ww.android.governmentheart.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.heart.HeartCoreFragment;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HeartFragment extends BaseFragment<MagicIndicatorView, VoidModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    public ArrayList<NewsTypeBean> mTypeBeans;

    public static HeartFragment newInstance(ArrayList<NewsTypeBean> typeBeans){
        HeartFragment heartFragment = new HeartFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("types",typeBeans);
        heartFragment.setArguments(bundle);
        return heartFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_heart;
    }

    @Override
    protected void init() {
        mTypeBeans = (ArrayList<NewsTypeBean>) getArguments().getSerializable("types");
        initViewPager();
        v.setTitles(Arrays.asList(getResources().getStringArray(R.array.together_text)));
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
        v.viewPager.setOffscreenPageLimit(3);
    }

    /**
     * 添加fragment
     */
    private void addFragment(){
        if (fragments==null){
            fragments = new ArrayList<>();
        }
        fragments.add(HeartCoreFragment.newInstance(1));
        fragments.add(HeartCoreFragment.newInstance(2));
        fragments.add(HeartCoreFragment.newInstance(3));
        fragments.add(HeartCoreFragment.newInstance(4));
    }
}
