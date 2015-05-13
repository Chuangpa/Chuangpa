package com.chuangpa.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import chuangpa.com.chuangpa.R;

/**
 * Created by Lan on 2015-05-13.
 */
public class ChatActivity extends ActionBarActivity {
    android.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActionBar ab = this.getSupportActionBar();
        ab.setTitle(getString(R.string.activity_actionbar_chart));
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.activated_background_color));
    }

}
