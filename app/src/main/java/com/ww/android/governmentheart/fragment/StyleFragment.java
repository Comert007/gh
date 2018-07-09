package com.ww.android.governmentheart.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.home.UserLocationActivity;
import com.ww.android.governmentheart.adapter.IndicatorPagerAdapter;
import com.ww.android.governmentheart.fragment.style.FarmFragment;
import com.ww.android.governmentheart.fragment.style.FeaturesFragment;
import com.ww.android.governmentheart.fragment.style.InterviewsFragment;
import com.ww.android.governmentheart.fragment.style.IntroduceFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.MagicIndicatorView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class StyleFragment extends BaseFragment<MagicIndicatorView, MainModel> {

    private IndicatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private List<NewsTypeBean> newsTypeBeans;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_style;
    }

    @Override
    protected void init() {
        newsCategory();
    }

    public void initPager(List<NewsTypeBean> newsTypeBeans) {
        if (newsTypeBeans!=null && newsTypeBeans.size() >= 8) {
            this.newsTypeBeans = newsTypeBeans.subList(4,8);

            List<String> titles = new ArrayList<>();
            for (NewsTypeBean newsTypeBean : this.newsTypeBeans) {
                titles.add(newsTypeBean.getName());
            }
            addFragment();
            initViewPager();

            v.setTitles(titles);
            v.initMagicIndicator(true);
        }
    }

    @OnClick({R.id.btn_title_left})
    public void onClick(){
        UserLocationActivity.launch(getContext());
    }

    /**
     * init viewpager
     */
    private void initViewPager() {
        fragmentManager = getChildFragmentManager();
        pagerAdapter = new IndicatorPagerAdapter(fragmentManager, fragments);
        v.viewPager.setAdapter(pagerAdapter);
        v.viewPager.setOffscreenPageLimit(4);
    }

    /**
     * 添加fragment
     */
    private void addFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(FeaturesFragment.newInstance(newsTypeBeans.get(0)));
        fragments.add(FarmFragment.newInstance(newsTypeBeans.get(1).getCode()));
        fragments.add(IntroduceFragment.newInstance(newsTypeBeans.get(2).getCode()));
        fragments.add(InterviewsFragment.newInstance(newsTypeBeans.get(3).getCode()));
    }


    private void newsCategory() {
        m.newsCategory(new BaseObserver<PageListBean<NewsTypeBean>>(getContext(), bindToLifecycle()) {
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
