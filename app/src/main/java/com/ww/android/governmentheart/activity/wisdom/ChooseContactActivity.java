package com.ww.android.governmentheart.activity.wisdom;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.ChooseContactAdapter;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.config.type.CodeType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ContactBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.ClearEditText;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import ww.com.core.Debug;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class ChooseContactActivity extends BaseActivity<RefreshView, WisdomModel> {

    @BindView(R.id.et_search)
    ClearEditText etSearch;


    private ChooseContactAdapter adapter;
    private int page;
    private String name;
    private ContactBean mContactBean;

    public static void start(Activity context) {
        Intent intent = new Intent(context, ChooseContactActivity.class);
        context.startActivityForResult(intent, CodeType.REQUEST_CHOOSE_CONTACT);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_choose_contact;
    }

    @Override
    protected void init() {
        initRecycler();
        initListener();
        v.srl.autoRefresh();
    }

    private void initListener() {
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (event.getAction() == EditorInfo.IME_ACTION_SEARCH) {
                String name = etSearch.getText().toString().replaceAll(" ", "");
                if (!TextUtils.isEmpty(name)) {
                    ChooseContactActivity.this.name = name;
                    contacts();
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        });
        if (v.srl == null) {
            return;
        }
        v.srl.setOnRefreshListener(refreshLayout -> {
            page = 0;
            contacts();
        });

        v.srl.setOnLoadMoreListener(refreshLayout -> contacts());

        adapter.setOnAdapterItemListener(new OnAdapterItemListener() {
            @Override
            public void onAdapterItem(View view, int position) {
                mContactBean = adapter.getItem(position);
                Debug.d("contact is null"+(mContactBean==null));
            }
        });


    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new ChooseContactAdapter(this);
        v.crv.setAdapter(adapter);

    }

    @OnClick({R.id.tv_cancel, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.btn_commit:
                resultFinish();
                break;
        }
    }


    private void contacts() {
        Map map = new HashMap();
        map.put("pageNo", page);
        if (!TextUtils.isEmpty(name)) {
            map.put("name", name);
        }
        m.contacts(map, new BaseObserver<PageListBean<ContactBean>>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<ContactBean> contactBeanPageListBean,
                                     @Nullable List<PageListBean<ContactBean>> list, @Nullable
                                             PageBean<PageListBean<ContactBean>> pageBean) {
                if (contactBeanPageListBean != null && contactBeanPageListBean.getList() != null
                        && contactBeanPageListBean.getList().size() > 0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<ContactBean> contactBeans = contactBeanPageListBean.getList();
                    PagingBean pagingBean = contactBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (contactBeans != null && contactBeans.size() > 0) {
                            adapter.addList(contactBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(contactBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                } else {

                    reload(EmptyLayout.STATUS_NO_DATA);
                }
            }

            @Override
            protected void onFailure() {
                super.onFailure();
                reload(EmptyLayout.STATUS_NO_NET);
            }
        });
    }

    private void reload(int type) {
        if (type == EmptyLayout.STATUS_NO_NET) {
            v.loadStatus(EmptyLayout.STATUS_NO_NET);
        } else {
            v.loadStatus(EmptyLayout.STATUS_NO_DATA);
        }
        v.mEmptyLayout.setRetryListener(new EmptyLayout.OnRetryListener() {
            @Override
            public void onRetry() {
                contacts();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }

    private void resultFinish() {
        if (mContactBean != null) {
            Debug.d("contact is not null");
            Intent intent = getIntent();
            intent.putExtra("contact", mContactBean);
            setResult(CodeType.RESULT_CHOOSE_CONTACT,intent);
            finish();
        } else {
            showToast("请选择一位联系人");
        }
    }

}
