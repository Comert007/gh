package com.ww.android.governmentheart.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.base.UserActivity;
import com.ww.android.governmentheart.activity.home.UserLocationActivity;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.heart.HeartCoreFragment;
import com.ww.android.governmentheart.fragment.heart.PolicyCoreFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import ww.com.core.Debug;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HeartFragment extends BaseFragment<MagicIndicatorView, MainModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    public List<NewsTypeBean> mTypeBeans;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_heart;
    }

    @Override
    protected void init() {
        newsCategory();
    }

    @Override
    public void onTitleRight() {
        super.onTitleRight();
        UserActivity.start(getActivity());
    }

    public void initPager(List<NewsTypeBean> mTypeBeans) {
        List<String> titles = new ArrayList<>();
        if (mTypeBeans != null && mTypeBeans.size() >= 4) {
            this.mTypeBeans = mTypeBeans.subList(0, 4);
            for (NewsTypeBean typeBean : this.mTypeBeans) {
                titles.add(typeBean.getName());
            }

            addFragment();
            initViewPager();

            v.setTitles(titles);
            v.initMagicIndicator(true);
        }

    }

    /**
     * init viewpager
     */
    private void initViewPager() {
        fragmentManager = getChildFragmentManager();
        pagerAdapter = new IndicatorPagerAdapter(fragmentManager, fragments);
        if (v.viewPager != null) {
            v.viewPager.setAdapter(pagerAdapter);
            v.viewPager.setOffscreenPageLimit(3);
        } else {
            Debug.d("===>>>>>> viewPager is null");
        }
    }

    /**
     * 添加fragment
     */
    private void addFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if (mTypeBeans == null) {
            return;
        }
        for (int i = 0; i < mTypeBeans.size(); i++) {
            NewsTypeBean typeBean = mTypeBeans.get(i);
            if (i == 3) {
                fragments.add(PolicyCoreFragment.newInstance(typeBean.getId()));
            } else {
                fragments.add(HeartCoreFragment.newInstance(typeBean.getCode()));
            }
        }
    }

    private void newsCategory() {
        m.newsCategory(new BaseObserver<PageListBean<NewsTypeBean>>(getContext(), bindToLifecycle
                ()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsTypeBean>
                                             newsTypeBeanPageListBean, @Nullable
                                             List<PageListBean<NewsTypeBean>> list, @Nullable
                                             PageBean<PageListBean<NewsTypeBean>> page) {
                if (newsTypeBeanPageListBean != null) {
                    List<NewsTypeBean> newsTypeBeans = newsTypeBeanPageListBean.getList();
                    if (newsTypeBeans != null) {
                        initPager(newsTypeBeans);
                    }
                }
            }
        });
    }

    @OnClick({R.id.btn_title_left})
    public void onClick(){
        UserLocationActivity.launch(getContext());
    }
}
