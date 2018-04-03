package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView mRv_my_client;
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
        mRv_my_client = (RecyclerView) findViewById(R.id.rv_my_client);
        myClientList = new ArrayList<>();
        myClientAdapter = new MyClientAdapter(myClientList, MyClient.this);
        mRv_my_client.setLayoutManager(new LinearLayoutManager(this));
        mRv_my_client.setAdapter(myClientAdapter);
        myClientAdapter.notifyDataSetChanged();
    }

}
