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
 * Created by Administrator on 2017/8/15.
 * 余额
 */

public class Ac_Remaining_Sum extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back, mTop_up,mWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_remaining_sum);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("余额");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        mTop_up = (RelativeLayout) findViewById(R.id.top_up);
        mWithdraw = (RelativeLayout) findViewById(R.id.withdraw);
        mTop_up.setOnClickListener(this);
        mWithdraw.setOnClickListener(this);
        InitData();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_up:
                startActivity(new Intent(Ac_Remaining_Sum.this, Ac_Top_Up.class));
                break;
            case R.id.withdraw:
                startActivity(new Intent(Ac_Remaining_Sum.this, Ac_Withdraw.class));
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}