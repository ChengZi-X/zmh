package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.MyClientAdapter;
import com.zmh.zz.zmh.modelinfo.MyClientInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 我的客户
 */

public class Ac_My_Client extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_my_client;
    private MyClientAdapter myClientAdapter;
    private List<MyClientInfo> myClientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_client);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("我的客户");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_my_client = (ListView) findViewById(R.id.lv_my_client);
        myClientList = new ArrayList<>();
        myClientAdapter = new MyClientAdapter(myClientList, Ac_My_Client.this);
        mLv_my_client.setAdapter(myClientAdapter);
        myClientAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

}
