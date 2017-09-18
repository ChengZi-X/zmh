package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 客户资料
 */

public class Ac_Client_Data extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mClient_business, mClient_fund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("600001 张三");
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_data;
    }

    private void FindViewById() {
        mClient_business = (RelativeLayout) findViewById(R.id.client_business);
        mClient_business.setOnClickListener(this);
        mClient_fund = (RelativeLayout) findViewById(R.id.client_fund);
        mClient_fund.setOnClickListener(this);
    }

    private void InitData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.client_business:
                startActivity(new Intent(Ac_Client_Data.this, Ac_Client_Business.class));
                break;
            case R.id.client_fund:
                startActivity(new Intent(Ac_Client_Data.this, Ac_Client_Fund.class));
                break;
        }
    }
}
