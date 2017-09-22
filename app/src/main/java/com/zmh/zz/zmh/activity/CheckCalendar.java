package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 签到日历
 */

public class CheckCalendar extends BaseActivity implements View.OnClickListener {
    private TextView mAggregate_score, mContinuous_sign_in;
    private ImageView Im_sign_one, Im_sign_two;
    private View View_one, View_two;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("签到日历");
        setRtTitle("规则");
        setRightBtnVisible(true);
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_check_calendar;//任意非空布局
    }

    //右键点击
    @Override
    protected void onClickRight() {
        startActivity(new Intent(CheckCalendar.this, ActivityRule.class));
    }
    private void FindViewById() {
        mAggregate_score = (TextView) findViewById(R.id.aggregate_score);
        mAggregate_score.setText("85");
        TextPaint tp = mAggregate_score.getPaint();
        tp.setFakeBoldText(true);
        mContinuous_sign_in = (TextView) findViewById(R.id.continuous_sign_in);
        SpannableString spanText = new SpannableString("已连续签到 2 天");
        spanText.setSpan(new ForegroundColorSpan(Color.parseColor("#FB6A6D")), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mContinuous_sign_in.setText(spanText);
        Im_sign_one = (ImageView) findViewById(R.id.sign_one);
        //Im_sign_one.setBackgroundResource(R.mipmap.sign_one);
        Im_sign_one.setImageDrawable(getResources().getDrawable((R.mipmap.sign_one)));
        Im_sign_two = (ImageView) findViewById(R.id.sign_two);
        Im_sign_two.setImageDrawable(getResources().getDrawable((R.mipmap.sign_two)));
        View_one = findViewById(R.id.view_one);
        View_one.setBackgroundColor(Color.parseColor("#8A320E"));
        View_two = findViewById(R.id.view_two);
        View_two.setBackgroundColor(Color.parseColor("#8A320E"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
