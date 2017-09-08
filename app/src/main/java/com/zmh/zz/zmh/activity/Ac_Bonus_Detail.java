package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.BonusDetailAdapter;
import com.zmh.zz.zmh.modelinfo.BonusDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on
 * 奖金明细
 */

public class Ac_Bonus_Detail extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_bonus_detail;
    private BonusDetailAdapter bonusDetailAdapter;
    private List<BonusDetailInfo> bonusDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bonus_detail);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("明细");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_bonus_detail = (ListView) findViewById(R.id.lv_bonus_detail);
        bonusDetailList = new ArrayList<>();
        bonusDetailAdapter = new BonusDetailAdapter(bonusDetailList, Ac_Bonus_Detail.this);
        mLv_bonus_detail.setAdapter(bonusDetailAdapter);
        bonusDetailAdapter.notifyDataSetChanged();
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
