package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ClientSumAdapter;
import com.zmh.zz.zmh.modelinfo.ClientSumInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户余额
 */

public class Ac_Client_Sum extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_client_sum;
    private ClientSumAdapter clientSumAdapter;
    private List<ClientSumInfo> clientSumtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_client_sum);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("客户余额");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_client_sum = (ListView) findViewById(R.id.lv_client_sum);
        clientSumtList = new ArrayList<>();
        clientSumAdapter = new ClientSumAdapter(clientSumtList, Ac_Client_Sum.this);
        mLv_client_sum.setAdapter(clientSumAdapter);
        clientSumAdapter.notifyDataSetChanged();
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
