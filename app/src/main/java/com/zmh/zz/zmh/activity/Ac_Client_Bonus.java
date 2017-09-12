package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ClienBonustAdapter;
import com.zmh.zz.zmh.modelinfo.ClientBonusInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户奖金
 */

public class Ac_Client_Bonus extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_client_bonust;
    private ClienBonustAdapter clienBonustAdapter;
    private List<ClientBonusInfo> clienBonustList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_client_bonus);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("客户奖金");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_client_bonust = (ListView) findViewById(R.id.lv_client_bonus);
        clienBonustList = new ArrayList<>();
        clienBonustAdapter = new ClienBonustAdapter(clienBonustList, Ac_Client_Bonus.this);
        mLv_client_bonust.setAdapter(clienBonustAdapter);
        clienBonustAdapter.notifyDataSetChanged();
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
