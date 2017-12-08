package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;


/**
 * Created by Administrator
 * 余额
 */

public class Sum extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mTop_up, mWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNotificationBar(R.color.f3_sum);
        setLtTitle("余额");
        setRtTitle("明细");
        setRightBtnVisible(true);
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#1B82D2"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setLtTitleTvColor(R.color.white);
        setRtTitleTvColor(R.color.white);
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_sum;//任意非空布局
    }

    private void FindViewById() {
        mTop_up = (RelativeLayout) findViewById(R.id.top_up);
        mWithdraw = (RelativeLayout) findViewById(R.id.withdraw);
        mTop_up.setOnClickListener(this);
        mWithdraw.setOnClickListener(this);
        InitData();
    }

    //右键点击
    @Override
    protected void onClickRight() {
        startActivity(new Intent(Sum.this, SumDetail.class));
    }

    private void InitData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_up:
                Intent intent =new Intent(Sum.this,AccountPut.class);
                startActivity(intent);
                break;
            case R.id.withdraw:
                startActivity(new Intent(Sum.this, AccountOut.class));
                break;
        }
    }
}