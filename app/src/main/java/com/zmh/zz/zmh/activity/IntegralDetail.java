package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.IntegralDetailAdapter;
import com.zmh.zz.zmh.modelinfo.IntegralDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 积分明细
 */

public class IntegralDetail extends BaseActivity {
    private ListView mLv_integral_detail;
    private IntegralDetailAdapter integralDetailAdapter;
    private List<IntegralDetailInfo> integralDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("积分明细");
        setRtTitle("规则");
        setRightBtnVisible(true);
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_integral_detail;
    }

    private void FindViewById() {
    }

    //右键点击
    @Override
    protected void onClickRight() {
        startActivity(new Intent(IntegralDetail.this, IntegralRule.class));
    }

    private void InitData() {
        mLv_integral_detail = (ListView) findViewById(R.id.lv_integral_detail);
        integralDetailList = new ArrayList<>();
        integralDetailAdapter = new IntegralDetailAdapter(integralDetailList, IntegralDetail.this);
        mLv_integral_detail.setAdapter(integralDetailAdapter);
        integralDetailAdapter.notifyDataSetChanged();

    }

}
