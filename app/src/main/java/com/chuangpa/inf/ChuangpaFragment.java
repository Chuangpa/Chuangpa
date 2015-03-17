package com.chuangpa.inf;

import android.view.View;

/**
 * Created by Lan on 2015-03-16.
 */
public interface ChuangpaFragment {
    /**
     * 刷新UI
     */
    void refresh(Object... params);
    /**
     * 初始化控件
     */
    void initView(View v);
}
