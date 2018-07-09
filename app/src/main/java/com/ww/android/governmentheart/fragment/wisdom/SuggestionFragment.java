package com.ww.android.governmentheart.fragment.wisdom;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.wisdom.AdviceActivity;
import com.ww.android.governmentheart.adapter.wisdom.SuggestionAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import ww.com.core.Debug;
import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class SuggestionFragment extends BaseFragment<RefreshView, WisdomModel> {

    private SuggestionAdapter adapter;
    private int page;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_suggestion;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
        v.srl.autoRefresh();
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                suggest();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                suggest();
            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()), RecyclerHelper
                .defaultMoreDecoration(getContext()));

        adapter = new SuggestionAdapter(getContext());
        v.crv.setAdapter(adapter);

    }

    @OnClick({R.id.btn_suggestion})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_suggestion:
                AdviceActivity.start(getContext());
                break;
            default:
                break;
        }
    }

    private void suggest() {
        UserBean userBean = (UserBean) BaseApplication.getInstance().getUserInfo();
        Debug.d("userBean==null?"+(userBean ==null));
        Debug.d("userBean.getUser()==null?"+(userBean.getUser() ==null));
        if (userBean == null || userBean.getUser() == null) {
            return;
        }
        Map map = new HashMap();
        map.put("userId", userBean.getUser().getId());
        map.put("pageNo", page);
        map.put("date", TimeUtils.date2String(new Date()));
        if (v.srl == null) {
            return;
        }
        m.suggest(map, new BaseObserver<PageListBean<SuggestBean>>(getContext(), bindToLifecycle
                ()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<SuggestBean> suggestBeanPageListBean,
                                     @Nullable List<PageListBean<SuggestBean>> list, @Nullable
                                             PageBean<PageListBean<SuggestBean>> pageBean) {

                if (suggestBeanPageListBean != null && suggestBeanPageListBean.getList() != null) {
                    List<SuggestBean> suggestBeans = suggestBeanPageListBean.getList();
                    PagingBean pagingBean = suggestBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (suggestBeans != null && suggestBeans.size() > 0) {
                            adapter.addList(suggestBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(suggestBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                }
            }
        });
    }
}
