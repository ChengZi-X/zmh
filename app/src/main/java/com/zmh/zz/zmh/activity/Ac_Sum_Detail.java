package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.SumDetailAdapter;
import com.zmh.zz.zmh.modelinfo.SumDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on
 * 余额明细
 */

public class Ac_Sum_Detail extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_sum_detail;
    private SumDetailAdapter sumDetailAdapter;
    private List<SumDetailInfo> sumDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sum_detail);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("明细");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_sum_detail = (ListView) findViewById(R.id.lv_sum_detail);
        sumDetailList = new ArrayList<>();
        sumDetailAdapter = new SumDetailAdapter(sumDetailList, Ac_Sum_Detail.this);
        mLv_sum_detail.setAdapter(sumDetailAdapter);
        sumDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}