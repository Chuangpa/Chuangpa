package com.chuangpa.util;

import java.util.Map;

/**
 * Created by Lan on 2015-03-17.
 */
public class Task {

    //任务ID
    private int taskId;

    //参数
    private Map<String, Object> taskParams;

    private int id;

    //登录
    public static final int POST_LOGIN_INFO = 0x5201315;
    //注册
    public static final int POST_REGISTER_INFO = 0x5201316;
    //发送消息
    public static final int SEND_MSG_NORMAL = 0x5201320;

    public Task(int taskId, Map<String, Object> taskParams)
    {
        this.taskId = taskId;
        this.taskParams = taskParams;
    }



    public int getTaskId()
    {
        return taskId;
    }



    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }



    public Map<String, Object> getTaskParams()
    {
        return taskParams;
    }



    public void setTaskParams(Map<String, Object> taskParams)
    {
        this.taskParams = taskParams;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }


}
