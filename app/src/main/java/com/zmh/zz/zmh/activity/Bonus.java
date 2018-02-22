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
 * 奖金
 */

public class Bonus extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mRoll_out, mShift_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNotificationBar(R.color.f3_bonus);
        setLtTitle("奖金");
        setRtTitle("明细");
        setRightBtnVisible(true);
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#F26100"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setLtTitleTvColor(R.color.white);
        setRtTitleTvColor(R.color.white);
        FindViewById();
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_bonus;
    }

    //右键点击
    @Override
    protected void onClickRight() {
        startActivity(new Intent(Bonus.this, BonusDetail.class));
    }

    private void FindViewById() {
        mRoll_out = (RelativeLayout) findViewById(R.id.roll_out);
        mShift_to = (RelativeLayout) findViewById(R.id.shift_to);
        mRoll_out.setOnClickListener(this);
        mShift_to.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roll_out:
                startActivity(new Intent(Bonus.this, BonusOut.class));
                break;
            case R.id.shift_to:
                startActivity(new Intent(Bonus.this, BonusPut.class));
                break;
        }
    }
}