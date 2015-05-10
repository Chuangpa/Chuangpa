package com.chuangpa.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.chuangpa.adapter.HomeListViewAdapter;
import com.chuangpa.bean.HomeInfo;
import com.chuangpa.inf.ChuangpaFragment;
import com.chuangpa.service.MainService;
import com.chuangpa.view.ObservableListView;
import com.chuangpa.view.ObservableScrollViewCallbacks;
import com.chuangpa.view.PathGroup;
import com.chuangpa.view.RefreshLayout;
import com.chuangpa.view.ScrollState;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import chuangpa.com.chuangpa.MainActivity;
import chuangpa.com.chuangpa.R;

/**
 * Created by Lan on 2015-03-16.
 */
public class HomeFragment extends MainActivity.PlaceholderFragment implements ChuangpaFragment,ObservableScrollViewCallbacks{
    private View v;
    private boolean initFlag;
    public static final String FRAGMENT_TAG = "actionBarControl";
    private static final Integer[] IMAGES = {R.drawable.item_btn_idea,R.drawable.item_btn_diary,R.drawable.item_btn_cteam};
    private static final String[] ADAPTER = {"写点子", "写日志", "建团队"};
    private String items[] = {"联系" , "分享到新浪微博", "分享到QQ", "收藏", "举报" };

    private ArrayList<HomeInfo> list;
    private  HomeListViewAdapter mAdapter;

    @Override
    public void refresh(Object... params) {

    }

    @Override
    public void initView(View v) {
        final ObservableListView listView = (ObservableListView) v.findViewById(R.id.list);

        final PathGroup pathGroup = (PathGroup) v.findViewById(R.id.path_group);



        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<3;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", IMAGES[i]);//添加图像资源的ID
            map.put("ItemText", ADAPTER[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
                lstImageItem,//数据来源
                R.layout.item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage","ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage,R.id.ItemText});
        pathGroup.setAdapter(saImageItems);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return pathGroup.getIsShow();
            }
        });

            pathGroup.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(pathGroup.mStatus==0) {
                        pathGroup.exeHide();
                        return true;
                    }else{
                        return false;
                    }
                }
            });



        pathGroup.setOnPathGroupListener(new PathGroup.OnPathGroupListener() {
            @Override
            public void onItemClick(int index) {
                Toast.makeText(getActivity(), ADAPTER[index], Toast.LENGTH_SHORT).show();
                pathGroup.exeHide();

            }

            @Override
            public void onPathHide(int index) {
                pathGroup.exeHide();
            }
        });



        listView.setScrollViewCallbacks(this);
//        setDummyData(listView);

        // 模拟一些数据
        final List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("item - " + i);
        }

        // 构造适配器
        final BaseAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                datas);



        // 获取RefreshLayout实例
        final RefreshLayout myRefreshListView = (RefreshLayout)
                v.findViewById(R.id.swipe_layout);



        list = new ArrayList<HomeInfo>();
        for(int i =0;i<=20;i++){

            HomeInfo homeInfo = new HomeInfo("用户"+i,"ic_lancher.png","2015-3-28",getResources().getString(R.string.test_content),i%3);

            list.add(homeInfo);
        }


        mAdapter = new HomeListViewAdapter(getActivity(),listView,list);


        // 设置下拉刷新时的颜色值,颜色值需要定义在xml中
        myRefreshListView
                .setColorScheme(R.color.primaryDark,
                                R.color.primary,
                                R.color.accent,
                                R.color.accent_material_light);
        // 设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // 更新数据


                        ArrayList<HomeInfo> mList = new ArrayList<HomeInfo>();
                        HomeInfo homeInfo = new HomeInfo("用户-1","ic_lancher.png","2015-3-28",getResources().getString(R.string.test_content2),1);
                        mList.add(homeInfo);
                        mList.addAll(list);
                        list = mList;
                        mAdapter.refreshData(mList);
                        // 更新完后调用该方法结束刷新
                        myRefreshListView.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        // 加载监听器
        myRefreshListView.setOnLoadListener(new RefreshLayout.OnLoadListener() {

            @Override
            public void onLoad() {

                Toast.makeText(getActivity(), "load", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        list.add(new HomeInfo("用户-1","ic_lancher.png","2015-3-28",getResources().getString(R.string.test_content2),2));
                        mAdapter.refreshData(list);
                        // 加载完后调用该方法
                        myRefreshListView.setLoading(false);

                    }
                }, 1500);

            }
        });



        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"第"+(position+1)+"条消息",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity());
                builder.setItems(items, new Dialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int id) {
                        // TODO Auto-generated method stub
                        switch (id) {
                            case 0:

                                break;
                            case 1:
                                //新浪微博分享
                                break;
                            case 2:
                                //QQ分享

                                break;
                            case 3:
                                //收藏
                                break;
                            case 4:

                                break;
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
                return false;
                }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
