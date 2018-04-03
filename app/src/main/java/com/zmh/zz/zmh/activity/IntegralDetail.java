package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView mRv_integral_detail;
    private IntegralDetailAdapter integralDetailAdapter;
    private List<IntegralDetailInfo> integralDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("积分明细");
        setRtTitle("规则");
        setRightBtnVisible(true);
        FindViewById();
        initData();
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

    private void initData() {
        mRv_integral_detail = (RecyclerView) findViewById(R.id.rv_integral_detail);
        integralDetailList = new ArrayList<>();
        integralDetailAdapter = new IntegralDetailAdapter(integralDetailList, IntegralDetail.this);
        mRv_integral_detail.setLayoutManager(new LinearLayoutManager(this));
        mRv_integral_detail.setAdapter(integralDetailAdapter);
        integralDetailAdapter.notifyDataSetChanged();

    }

}
