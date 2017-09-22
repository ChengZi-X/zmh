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
 * 奖金
 */

public class Bonus extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mRoll_out, mShift_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("奖金");
        setRtTitle("明细");
        setRightBtnVisible(true);
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#F26100"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setTitleTvColor(Color.parseColor("#FFFFFF"));
        setRtTitleTvColor(Color.parseColor("#FFFFFF"));
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_bonus;//任意非空布局
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

    private void InitData() {
        //因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.c3_bonus));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roll_out:
                startActivity(new Intent(Bonus.this, RollOut.class));
                break;
            case R.id.shift_to:
                startActivity(new Intent(Bonus.this, ShiftTo.class));
                break;
        }
    }
}