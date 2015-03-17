package com.chuangpa.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuangpa.inf.ChuangpaFragment;
import com.chuangpa.service.MainService;
import com.chuangpa.view.ObservableListView;
import com.chuangpa.view.ObservableScrollViewCallbacks;
import com.chuangpa.view.ScrollState;

import chuangpa.com.chuangpa.MainActivity;
import chuangpa.com.chuangpa.R;

/**
 * Created by Lan on 2015-03-16.
 */
public class HomeFragment extends MainActivity.PlaceholderFragment implements ChuangpaFragment,ObservableScrollViewCallbacks{
    private View v;
    private boolean initFlag;
    public static final String FRAGMENT_TAG = "actionBarControl";
    @Override
    public void refresh(Object... params) {

    }

    @Override
    public void initView(View v) {
        ObservableListView listView = (ObservableListView) v.findViewById(R.id.list);
        listView.setScrollViewCallbacks(this);
        setDummyData(listView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBarActivity activity = (ActionBarActivity) getActivity();
        ActionBar ab = activity.getSupportActionBar();
        ab.show();
        if (v == null) {

            v = inflater.inflate(R.layout.fragment_home,container,false);
            MainService.addFragment(this);
            initView(v);
        }
        // 缓存的v需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBarActivity activity = (ActionBarActivity) getActivity();
        if (activity == null) {
            return;
        }
        ActionBar ab = activity.getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                    ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {

            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }

}
