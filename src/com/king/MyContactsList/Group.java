package com.king.MyContactsList;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/19.
 */
public class Group {
    private String name;

    private ArrayList<MyContact> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MyContact> getList() {
        return list;
    }

    public void setList(ArrayList<MyContact> list) {
        this.list = list;
    }
}
