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
 * 客户资金
 */

public class Ac_Client_Fund extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back, mClient_top_up, mClient_bonus, mClient_sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_client_fund);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("客户资金");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        mClient_top_up = (RelativeLayout) findViewById(R.id.client_top_up);
        mClient_bonus = (RelativeLayout) findViewById(R.id.client_bonus);
        mClient_sum = (RelativeLayout) findViewById(R.id.client_sum);
        mClient_top_up.setOnClickListener(this);
        mClient_bonus.setOnClickListener(this);
        mClient_sum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.client_top_up:
                startActivity(new Intent(Ac_Client_Fund.this, Ac_Client_Top_Up.class));
                break;
            case R.id.client_bonus:
                startActivity(new Intent(Ac_Client_Fund.this, Ac_Client_Bonus.class));
                break;
            case R.id.client_sum:
                startActivity(new Intent(Ac_Client_Fund.this, Ac_Client_Sum.class));
                break;
        }
    }
}
