package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        setTitle("余额");
        setRtTitle("明细");
        setRightBtnVisible(true);
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#1B82D2"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setTitleTvColor(Color.parseColor("#FFFFFF"));
        setRtTitleTvColor(Color.parseColor("#FFFFFF"));
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
        //因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.c3_sum));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_up:
                startActivity(new Intent(Sum.this, TopUp.class));
                break;
            case R.id.withdraw:
                startActivity(new Intent(Sum.this, Withdraw.class));
                break;
        }
    }
}