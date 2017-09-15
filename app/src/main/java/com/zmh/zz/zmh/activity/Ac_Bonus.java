package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 奖金
 */

public class Ac_Bonus extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle, mBonus_detail;
    private RelativeLayout mTitle_back, mRoll_out, mShift_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bonus);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("奖金");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mRoll_out = (RelativeLayout) findViewById(R.id.roll_out);
        mShift_to = (RelativeLayout) findViewById(R.id.shift_to);
        mBonus_detail = (TextView) findViewById(R.id.bonus_detail);
        mBonus_detail.setOnClickListener(this);
        mTitle_back.setOnClickListener(this);
        mRoll_out.setOnClickListener(this);
        mShift_to.setOnClickListener(this);
        InitData();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bonus_detail:
                startActivity(new Intent(Ac_Bonus.this, Ac_Bonus_Detail.class));
                break;
            case R.id.roll_out:
                startActivity(new Intent(Ac_Bonus.this, Ac_Roll_Out.class));
                break;
            case R.id.shift_to:
                startActivity(new Intent(Ac_Bonus.this, Ac_Shift_To.class));
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}