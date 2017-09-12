package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 客户资料
 */

public class Ac_Client_Data extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back, mClient_business, mClient_fund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_client_data);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("600001 张三");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        mClient_business = (RelativeLayout) findViewById(R.id.client_business);
        mClient_business.setOnClickListener(this);
        mClient_fund = (RelativeLayout) findViewById(R.id.client_fund);
        mClient_fund.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.client_business:
                startActivity(new Intent(Ac_Client_Data.this, Ac_Client_Business.class));
                break;
            case R.id.client_fund:
                startActivity(new Intent(Ac_Client_Data.this, Ac_Client_Fund.class));
                break;
        }
    }
}
