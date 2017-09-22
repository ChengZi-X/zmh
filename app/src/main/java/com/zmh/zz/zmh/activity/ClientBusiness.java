package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.view.View;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 客户业务
 */

public class ClientBusiness extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("客户业务");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_business;
    }

    private void FindViewById() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
