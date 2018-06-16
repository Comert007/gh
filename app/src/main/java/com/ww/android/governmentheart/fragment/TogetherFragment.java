package com.ww.android.governmentheart.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.together.ActivityFragment;
import com.ww.android.governmentheart.fragment.together.OnlineFragment;
import com.ww.android.governmentheart.fragment.together.PublishFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class TogetherFragment extends BaseFragment<MagicIndicatorView,VoidModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_together;
    }

    @Override
    protected void init() {
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
        fragments.add(new PublishFragment());
        fragments.add(new ActivityFragment());
        fragments.add(new OnlineFragment());
    }
}
