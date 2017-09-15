package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.IntegralDetailAdapter;
import com.zmh.zz.zmh.modelinfo.IntegralDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 积分明细
 */

public class Ac_Integral_Detail extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mTitle_back;
    private ListView mLv_integral_detail;
    private IntegralDetailAdapter integralDetailAdapter;
    private List<IntegralDetailInfo> integralDetailList;
    private TextView toolbartitle, mTitle_rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_integral_detail);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("积分明细");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_rule = (TextView) findViewById(R.id.rule);
        mTitle_rule.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_integral_detail = (ListView) findViewById(R.id.lv_integral_detail);
        integralDetailList = new ArrayList<>();
        integralDetailAdapter = new IntegralDetailAdapter(integralDetailList, Ac_Integral_Detail.this);
        mLv_integral_detail.setAdapter(integralDetailAdapter);
        integralDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rule:
                startActivity(new Intent(Ac_Integral_Detail.this, Ac_Integral_Rule.class));
                break;
        }
    }
}
