package com.chuangpa.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuangpa.inf.ChuangpaFragment;
import com.chuangpa.service.MainService;

import chuangpa.com.chuangpa.MainActivity;
import chuangpa.com.chuangpa.R;

/**
 * Created by Lan on 2015-03-16.
 */
public class TeamFragment extends MainActivity.PlaceholderFragment implements ChuangpaFragment {
    private View v;
    @Override
    public void refresh(Object... params) {

    }

    @Override
    public void initView(View v) {
        ActionBarActivity activity = (ActionBarActivity) getActivity();
        ActionBar ab = activity.getSupportActionBar();
        ab.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (v == null) {

            v = inflater.inflate(R.layout.fragment_team,container,false);
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
}

