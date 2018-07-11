package com.ww.android.governmentheart.fragment.together;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.together.ActLeftAdapter;
import com.ww.android.governmentheart.adapter.together.PartMenuAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.bean.together.ActLeftBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class ActFragment extends BaseFragment<VoidView,VoidModel> {

    @BindView(R.id.rv)
    RecyclerView rv;

    private ActLeftAdapter leftAdapter;
    private PartMenuAdapter menuAdapter;
    private List<Fragment> fragments;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_act;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        leftAdapter = new ActLeftAdapter(getContext());
        rv.setLayoutManager(RecyclerHelper.defaultManager(getContext()));
        rv.setAdapter(leftAdapter);
        List<ActLeftBean> actLeftBeans = Arrays.asList(new ActLeftBean("即将开始"),
                new ActLeftBean("正在进行"),
                new ActLeftBean("已结束"));
        leftAdapter.addList(actLeftBeans);

        createFragments(actLeftBeans);
    }


    private void createFragments(List<ActLeftBean> actLeftBeans){
        fragments = new ArrayList<>();
        for (int i = 0; i < actLeftBeans.size(); i++) {
            fragments.add(ActivityFragment.newInstance((i+1)+""));
        }

        menuAdapter = new PartMenuAdapter(this,fragments,R.id.main_content);
        leftAdapter.setOnMenuChangeListener(new ActLeftAdapter.OnMenuChangeListener() {
            @Override
            public void onMenuChange(int position) {
                menuAdapter.change(position);
            }
        });
        leftAdapter.changeMenu(0);
        menuAdapter.change(0);
    }

}
