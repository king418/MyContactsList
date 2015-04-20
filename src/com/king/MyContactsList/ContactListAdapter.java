package com.king.MyContactsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/19.
 */

/**
 * 针对ExpandableListAdapter
 */
public class ContactListAdapter extends BaseExpandableListAdapter {

    private ArrayList<Group> data;
    private Context context;
    private LayoutInflater inflater;

    public ContactListAdapter(ArrayList<Group> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 返回有多少个组
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        //模拟通讯录 A-Z  #
        return data.size();
    }

    /**
     * @param i
     * @return
     */
    @Override
    public int getChildrenCount(int i) {
        return data.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        return data.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i * 1000 + i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 获取 组显示的控件界面
     * @param i  组位置
     * @param b  是否展开  每次点击展开或者收起都会调用这个方法 true/false 不同
     * @param view  用于复用的
     * @param viewGroup  父容器
     * @return
     */

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
       View ret = null;
        Group group = data.get(i);
        String name= group.getName();
        ret = inflater.inflate(R.layout.group_contact,viewGroup,false);

        TextView txtTitle = (TextView) ret.findViewById(R.id.group_title);
        txtTitle.setText(name);
        return ret;
    }

    /**
     * 获取子内容的界面
     * @param i  组的位置
     * @param i1  子元素的位置
     * @param b   是否是最后一个元素
     * @param view  复用布局
     * @param viewGroup  父容器
     * @return
     */

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View ret = null;

        Group group = data.get(i);

        ArrayList<MyContact> list = group.getList();
        MyContact myContact = list.get(i1);

        ret = inflater.inflate(R.layout.child_contact, viewGroup, false);

        TextView txtName = (TextView) ret.findViewById(R.id.child_name);

       txtName.setText( myContact.getName());
        return ret;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
