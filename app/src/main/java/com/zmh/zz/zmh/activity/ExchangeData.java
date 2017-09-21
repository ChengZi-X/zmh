package com.zmh.zz.zmh.activity;

import android.os.Bundle;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 兑换详情
 */

public class ExchangeData extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("权益兑换");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_exchange_data;
    }

    private void FindViewById() {
    }
}
