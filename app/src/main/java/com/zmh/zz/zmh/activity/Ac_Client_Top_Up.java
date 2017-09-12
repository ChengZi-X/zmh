package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ClientTopUpAdapter;
import com.zmh.zz.zmh.modelinfo.ClientTopUpInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户充值
 */

public class Ac_Client_Top_Up extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_client_top_up;
    private ClientTopUpAdapter clientTopUpAdapter;
    private List<ClientTopUpInfo> clientTopUptList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_client_top_up);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("客户充值");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_client_top_up = (ListView) findViewById(R.id.lv_client_top_up);
        clientTopUptList = new ArrayList<>();
        clientTopUpAdapter = new ClientTopUpAdapter(clientTopUptList, Ac_Client_Top_Up.this);
        mLv_client_top_up.setAdapter(clientTopUpAdapter);
        clientTopUpAdapter.notifyDataSetChanged();
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
