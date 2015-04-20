package com.king.MyContactsList;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends Activity implements ExpandableListView.OnGroupClickListener {

    private ArrayList<Group> data;
    private ExpandableListView listView;
    private LinearLayout indicator;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ExpandableListView) findViewById(R.id.contacts_list);


        if (listView != null) {
            //实现自定义的
            //设置组的点击事件，这个点击其实不去设置操作
            listView.setOnGroupClickListener(this);
            listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                /**
                 * 当groupPsoition指定的组当中的第childPosition元素点击的回调
                 * @param parent
                 * @param v
                 * @param groupPosition
                 * @param childPosition
                 * @param id
                 * @return
                 */

                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    return false;
                }
            });

            char ch = 'A';
            data = new ArrayList<Group>();
            for (int i = 0; i < 27; i++) {
                Group group = new Group();
                group.setName(Character.toString(ch));


                ArrayList<MyContact> list = new ArrayList<MyContact>();
                for (int j = 0; j < 5; j++) {
                    MyContact contacts = new MyContact();
                    contacts.setName(ch + " " + j);
                    list.add(contacts);
                }
                group.setList(list);
                ch++;
                data.add(group);
            }

            listView.setAdapter(new ContactListAdapter(data, this));
            int size = data.size();
            //指定索引，展开组
            for (int i = 0; i < size; i++) {
                listView.expandGroup(i);
            }
        }

        indicator = (LinearLayout) findViewById(R.id.indicator);

        indicator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int id = v.getId();
                boolean ret = false;

                if (id == R.id.indicator) {
                    int action = event.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            ret = true;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            //自身高度
                            int height = indicator.getHeight();
                            //获取标题个数
                            int childCount = indicator.getChildCount();
                            //每一个的高度
                            int i = height / childCount;
                            //事件的Y
                            int y = (int) event.getY();

                            int num = y / i;

                            int cc = y % i;

                            if (cc == 0) {
                                num--;
                            }
                            if (num < 0) {
                                num = 0;
                            }
                            listView.setSelectedGroup(num);

                            break;
                    }
                }


                return ret;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //选中并且滚动Group到顶部
       // listView.setSelectedGroup(3);

    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }
}
