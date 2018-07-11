package com.ww.android.governmentheart.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.home.UserLocationActivity;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.wisdom.KnowledgeFragment;
import com.ww.android.governmentheart.fragment.wisdom.SuggestionFragment;
import com.ww.android.governmentheart.fragment.wisdom.TransmissionFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class WisdomFragment extends BaseFragment<MagicIndicatorView,VoidModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_wisdom;
    }

    @Override
    protected void init() {
        addFragment();
        initViewPager();
        v.setTitles(Arrays.asList(getResources().getStringArray(R.array.wisdom_text)));
        v.initMagicIndicator(true);
    }


    @OnClick({R.id.btn_title_left})
    public void onClick(){
        UserLocationActivity.launch(getContext());
    }
    /**
     * init viewpager
     */
    private void initViewPager(){
        fragmentManager = getChildFragmentManager();
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
        fragments.add(new SuggestionFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new TransmissionFragment());
    }
}
