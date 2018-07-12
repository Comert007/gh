package com.ww.android.governmentheart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.join.JoinAdapter;
import com.ww.android.governmentheart.adapter.together.PartMenuAdapter;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.fragment.together.JoinOrganizationFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class JoinFragment extends BaseFragment<RefreshView, CommonModel> {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindColor(R.color.color_black)
    int colorBlack;

    private JoinAdapter adapter;
    private NewsTypeBean typeBean;
    private PartMenuAdapter menuAdapter;
    private List<Fragment> fragments;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_join;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        initData();
        initRecycler();
        initListener();
        news();
    }

    public static JoinFragment newInstance(NewsTypeBean newsTypeBean) {
        JoinFragment fragment = new JoinFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", newsTypeBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initData() {
        typeBean = (NewsTypeBean) getArguments().getSerializable("type");
    }

    private void initListener() {
        adapter.setOnAdapterItemListener(new OnAdapterItemListener() {
            @Override
            public void onAdapterItem(View view, int position) {
                menuAdapter.change(position);
            }
        });

    }

    private void initRecycler() {
        rv.setLayoutManager(RecyclerHelper.defaultManager(getContext()));
        adapter = new JoinAdapter(getContext());
        rv.setAdapter(adapter);
    }

    private void news() {
        Map map = new HashMap();
        map.put("code", typeBean.getCode());
        map.put("pageNo", 0);
        map.put("date", TimeUtils.date2String(new Date()));
        map.put("pageSize", 100);
        m.news(map, new BaseObserver<PageListBean<NewsBean>>(getContext(), bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsBean> newsBeanPageListBean,
                                     @Nullable List<PageListBean<NewsBean>> list, @Nullable
                                             PageBean<PageListBean<NewsBean>> pageBean) {

                if (newsBeanPageListBean != null && newsBeanPageListBean.getList() != null &&
                        newsBeanPageListBean.getList().size() > 0) {
                    List<NewsBean> newsBeans = newsBeanPageListBean.getList();
                    PagingBean pagingBean = newsBeanPageListBean.getPage();
                    adapter.addList(newsBeans);
                    createFragments(newsBeans);
                }
            }
        });
    }


    private void createFragments(List<NewsBean> newsBeans) {
        fragments = new ArrayList<>();
        for (NewsBean newsBean : newsBeans) {
            fragments.add(JoinOrganizationFragment.newInstance(newsBean.getUrl()));
        }
        menuAdapter = new PartMenuAdapter(this, fragments, R.id.main_content);
        adapter.selected(0);
        menuAdapter.change(0);
    }
}
