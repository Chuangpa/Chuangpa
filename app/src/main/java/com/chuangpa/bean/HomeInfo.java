package com.chuangpa.bean;

/**
 * Created by Lan on 2015-03-27.
 */
public class HomeInfo {

    private String name;
    private String headPhoto;
    private String date;
    private String content;
    private int type;

    public HomeInfo(String name,String headPhoto,String date,String content,int type){
        this.name = name;
        this.headPhoto = headPhoto;
        this.date = date;
        this.content = content;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
