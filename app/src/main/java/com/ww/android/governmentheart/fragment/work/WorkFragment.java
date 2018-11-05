package com.ww.android.governmentheart.fragment.work;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.base.UserActivity;
import com.ww.android.governmentheart.activity.work.PortraitActivity;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.fragment.wisdom.TransmissionFragment;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

public class WorkFragment extends BaseFragment<MagicIndicatorView, WorkModel> {


    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private boolean isOffice = true;


    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_work;
    }

    @Override
    public void onTitleRight() {
        super.onTitleRight();
        UserActivity.start(getActivity());
//        NotifyContentActivity.start(getContext(),"b981a7cac3254db5b268e336a8c73d1c");
    }

    @OnClick({R.id.btn_title_left})
    public void onClick(){
        PortraitActivity.start(getContext());
    }

    @Override
    protected void init() {
        UserBean userBean = (UserBean) BaseApplication.getInstance().getUserInfo();
        isOffice = userBean.getUser().isOffice();
        addFragment();
        initViewPager();

        v.setTitles(Arrays.asList(getResources().getStringArray(isOffice?R.array.work_text:R.array.work_text_short)));
        v.initMagicIndicator(true);
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
        fragments.add(new MessageFragment());
        if (isOffice){
            fragments.add(new TransmissionFragment());
        }
        fragments.add(new NotifyFragment());
    }

}
