package com.ww.android.governmentheart.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.home.UserLocationActivity;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.together.ActFragment;
import com.ww.android.governmentheart.fragment.together.OnlineFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class TogetherFragment extends BaseFragment<MagicIndicatorView, MainModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private List<NewsTypeBean> mTypeBeans;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_together;
    }

    @Override
    protected void init() {
        newsCategory();
    }


    public void initPager(List<NewsTypeBean> newsTypeBeans) {
        if (newsTypeBeans != null && newsTypeBeans.size() >= 9) {
            this.mTypeBeans = newsTypeBeans.subList(8, 9);
            addFragment();
            initViewPager();
            v.setTitles(Arrays.asList(getContext().getResources().getStringArray(R.array.together_text)));
            v.initMagicIndicator(true);
        }
    }


    @OnClick({R.id.btn_title_left})
    public void onClick() {
        UserLocationActivity.launch(getContext());
    }

    /**
     * init viewpager
     */
    private void initViewPager() {
        fragmentManager = getChildFragmentManager();
        pagerAdapter = new IndicatorPagerAdapter(fragmentManager, fragments);
        v.viewPager.setAdapter(pagerAdapter);
        v.viewPager.setOffscreenPageLimit(2);
    }

    /**
     * 添加fragment
     */
    private void addFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
//        fragments.add(JoinFragment.newInstance(this.mTypeBeans.get(0)));
        fragments.add(new ActFragment());
        fragments.add(new OnlineFragment());
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
}
