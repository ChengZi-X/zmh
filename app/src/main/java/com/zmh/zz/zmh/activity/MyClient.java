package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.MyClientAdapter;
import com.zmh.zz.zmh.modelinfo.MyClientInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 我的客户
 */

public class MyClient extends BaseActivity {
    private ListView mLv_my_client;
    private MyClientAdapter myClientAdapter;
    private List<MyClientInfo> myClientList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("我的客户");
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_my_client;
    }

    private void initData() {
        mLv_my_client = (ListView) findViewById(R.id.lv_my_client);
        myClientList = new ArrayList<>();
        myClientAdapter = new MyClientAdapter(myClientList, MyClient.this);
        mLv_my_client.setAdapter(myClientAdapter);
        myClientAdapter.notifyDataSetChanged();
    }

}
