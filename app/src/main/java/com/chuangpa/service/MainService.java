package com.chuangpa.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.chuangpa.inf.ChuangpaFragment;
import com.chuangpa.net.HttpDownload;
import com.chuangpa.util.Task;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Lan on 2015-03-17.
 */
public class MainService extends Service implements Runnable {
    // 任务队列
    private static Queue<Task> tasks = new LinkedList<Task>();
    private static ArrayList<ChuangpaFragment> fragments = new ArrayList<ChuangpaFragment>();
    // 是否运行线程
    private static boolean isRun;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            ChuangpaFragment fragment;
            switch (msg.what) {
                case Task.POST_LOGIN_INFO:// 用户登录

                    //更新UI
                    fragment = (ChuangpaFragment) getFragmentByName("LoginActivity");
                    fragment.refresh(msg.obj);
                    break;

                default:
                    break;
            }
        };
    };


    /**
     * 添加一个EpisodeClientFragment对象
     *
     * @param fragment
     */
    public static void addFragment(ChuangpaFragment fragment) {

        fragments.add(fragment);
    }



    private ChuangpaFragment getFragmentByName(String name) {
        ArrayList<ChuangpaFragment> list = new ArrayList<ChuangpaFragment>();
        if (!fragments.isEmpty()) {
            for (ChuangpaFragment fragment : fragments) {
                if (null != fragment) {
                    if (fragment.getClass().getName().indexOf(name) > 0) {
                        list.add(fragment);
                    }
                }
            }
        }

        if (list.size() == 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * 添加任务到任务队列中
     *
     * @param t
     */
    public static void newTask(Task t) {
        Log.i("MainService", "is runnable" + isRun);
        tasks.add(t);
    }
    @Override
    public void onCreate()
    {
        isRun = true;
        System.out.println("onCreate");
        Thread thread = new Thread(this);
        thread.start();

        super.onCreate();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (isRun) {
            Task task = null;
            if (!tasks.isEmpty()) {
                task = tasks.poll();// 执行完任务后把改任务从任务队列中移除
                if (null != task) {
                    doTask(task);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    //
    // 处理任务
    private void doTask(Task t) {
        Message msg = handler.obtainMessage();
        msg.what = t.getTaskId();
        String url;
        List<NameValuePair> params;
        String result;
        switch (t.getTaskId()) {
            case Task.POST_LOGIN_INFO:
                System.out.println("正在登录");
                url = "/client/BMSAction!login.action";
                params = new ArrayList<NameValuePair>();
                // 表单参数
                params.add(new BasicNameValuePair("account_id", t.getTaskParams().get("account_id")+""));
                params.add(new BasicNameValuePair("account_pwd", t.getTaskParams().get("account_pwd")+""));
                result = HttpDownload.sendPostHttpRequest(url, params);
                msg.obj= result;
                break;

            default:
                break;
        }

        handler.sendMessage(msg);
        ///latitude:29.60848
        ///longitude:105.071981
    }
}
