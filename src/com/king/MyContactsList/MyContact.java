package com.king.MyContactsList;

/**
 * Created by Administrator on 2015/4/19.
 */

/**
 * 联系人数据
 */
public class MyContact {
    /**
     * 姓名
     */
    private String name;
    /**
     * 拼音首字母
     */
    private char firstChar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(char firstChar) {
        this.firstChar = firstChar;
    }
}
