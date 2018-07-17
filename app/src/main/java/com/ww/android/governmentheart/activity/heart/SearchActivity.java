package com.ww.android.governmentheart.activity.heart;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.SearchAdapter;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.home.SearchView;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.utils.SharedPreferenceUtils;
import com.ww.android.governmentheart.utils.ToastUtils;
import com.ww.android.governmentheart.widget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author feng
 * @Date 2018/6/20
 */
public class SearchActivity extends BaseActivity<SearchView, VoidModel> {

    @BindView(R.id.et_search)
    ClearEditText etSearch;

    private SearchAdapter adapter;
    private ArrayList<String> history;

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        initRecycler();
        initListener();
        initHistory();
//        v.initFluid(Arrays.asList("统战", "民主人士", "新路子", "统一战线", "统战信息化", "创新", "见面会",
//                "平安崇州", "党代会", "乡村"));
    }

    private void initListener() {
        adapter.setOnAdapterItemListener(new OnAdapterItemListener() {
            @Override
            public void onAdapterItem(View view, int position) {
                String searchStr = adapter.getItem(position);
                etSearch.setText(searchStr);
                saveHistory(searchStr);
            }
        });
        if (v.srl == null) {
            return;
        }
        v.setRefreshType(RefreshType.NOT_ENABLE);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchStr = etSearch.getText().toString().replaceAll(" ", "");
                    if (!TextUtils.isEmpty(searchStr)) {
                        saveHistory(searchStr);
                        return true;
                    } else {
                        ToastUtils.showToast("请输入搜索内容");
                        return false;
                    }
                } else {
                    return false;
                }

            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultVerticalNotScrollManager(this),
                RecyclerHelper.defaultSingleDecoration(this));

        adapter = new SearchAdapter(this);
        v.crv.setAdapter(adapter);
    }

    private void initHistory() {
        String json = SharedPreferenceUtils.getInstance(this).getStringValue("history", "");
        if (TextUtils.isEmpty(json)) {
            history = new ArrayList<>();
        } else {
            history = new Gson().fromJson(json, new TypeToken<List<String>>() {
            }.getType());
        }

        if (history.size() > 0) {
            adapter.addList(history);
        }
    }

    @OnClick({R.id.tv_cancel, R.id.linear_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.linear_clear:
                SharedPreferenceUtils.getInstance(this).clear();
                adapter.getList().clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    private void saveHistory(String searchStr) {
        if (history.contains(searchStr)) {
            int pos = history.indexOf(searchStr);
            history.remove(pos);
            history.add(0, searchStr);
        } else {
            if (history.size() >= 10) {
                history.remove(history.size() - 1);
                history.add(0, searchStr);
            } else {
                history.add(searchStr);
            }
        }

        String json = new Gson().toJson(history);
        SharedPreferenceUtils.getInstance(this).setValue("history", json);

        SearchResultActivity.start(this, searchStr);
        adapter.addList(history);
    }

}
