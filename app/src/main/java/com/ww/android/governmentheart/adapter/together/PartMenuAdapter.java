package com.ww.android.governmentheart.adapter.together;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.View;

import com.ww.android.governmentheart.R;

import java.util.List;

public class PartMenuAdapter {

    private List<Fragment> mMenuFragment;
    private FragmentActivity mFragmentActivity;
    private Fragment mFragment;
    private int mFragmentContentId;
    private int mCurrentMenu = -1;
    private boolean mAnimationFlag = false;

    /**
     * @param activity          fragment activity
     * @param list              menu fragment list
     * @param fragmentContentId main fragment content
     */
    public PartMenuAdapter(FragmentActivity activity,
                           List<Fragment> list, int fragmentContentId) {
        this.mMenuFragment = list;
        this.mFragmentActivity = activity;
        this.mFragmentContentId = fragmentContentId;
    }


    public PartMenuAdapter(Fragment fragment,
                           List<Fragment> list, int fragmentContentId) {
        this.mMenuFragment = list;
        this.mFragment = fragment;
        this.mFragmentContentId = fragmentContentId;
    }

    public void change(int index) {
        if (mCurrentMenu == index) {
            return;
        }
        changeMenu(index);
    }

    /**
     * when click the menu button,if the view has been added in the fragment ,
     * it will be exec method onResume(),then show it, if not,it will be added
     * into the fragment and show it.
     *
     * @param index the menu view list index
     */
    public void changeMenu(int index) {
        Fragment fragment = this.mMenuFragment.get(index);
        FragmentTransaction ft = obtainFragmentTransaction(index);

        Fragment currFragment = getCurrentMenuFragment();
        if (currFragment != null) {
            currFragment.setUserVisibleHint(false);
            currFragment.onPause();
        }

        if (fragment.isAdded()) {
            fragment.setUserVisibleHint(true);
            fragment.onResume();
        } else {
            ft.add(mFragmentContentId, fragment, "frag_tag_" + index);
            fragment.setUserVisibleHint(true);
        }
        showMenuContent(index);
        ft.commit();
    }

    /**
     * show the selected fragment and hide others
     *
     * @param index the menu view list index
     */
    private void showMenuContent(int index) {
        int size = this.mMenuFragment.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = this.mMenuFragment.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(i);

            if (index == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
        mCurrentMenu = index; // set current menu
    }

    /**
     * set animation flag.
     *
     * @param b boolean
     */
    public void setIsAnimation(boolean b) {
        this.mAnimationFlag = b;
    }

    /**
     * if the mAnimationFlag is true,when two fragment are exchanging,it will
     * have animation.
     *
     * @param index the menu view list index
     * @return object of FragmentTransaction
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft =null;
        if (mFragmentActivity !=null){
            ft = this.mFragmentActivity.getSupportFragmentManager().beginTransaction();
        }else {
            ft = this.mFragment.getChildFragmentManager().beginTransaction();
        }
        if (this.mAnimationFlag) {
            // set animation
            if (index > mCurrentMenu) {
                ft.setCustomAnimations(R.anim.slide_left_in,
                        R.anim.slide_left_out);
            } else {
                ft.setCustomAnimations(R.anim.slide_right_in,
                        R.anim.slide_right_out);
            }
        }

        return ft;
    }

    /**
     * get the id which have been selected
     *
     * @return the selected index of the menu list
     */
    public int getCurrentMenu() {
        return mCurrentMenu;
    }

    /**
     * get the fragment which have been selected
     *
     * @return the selected fragment
     */
    public Fragment getCurrentMenuFragment() {
        if (mCurrentMenu < 0 || mCurrentMenu >= mMenuFragment.size()) {
            return null;
        }
        return this.mMenuFragment.get(mCurrentMenu);
    }

    public Fragment getMenuFragment(int i) {
        return this.mMenuFragment.get(i);
    }

    class MyGestureDetector extends GestureDetector {
        View view;

        public MyGestureDetector(Context context, OnGestureListener listener) {
            super(context, listener);
        }

        public void setView(View vi) {
            view = vi;
        }

        public View getView() {
            return view;
        }
    }

    public interface OnMenuClickListener extends View.OnClickListener {
        void onDoubleClick(View view);
    }
}
