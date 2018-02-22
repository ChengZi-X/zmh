package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;


/**
 * Created by Administrator
 * 基本信息
 */

public class EssentialInformation extends BaseActivity {
    private TextView mChoose_address, mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("基本信息");
        FindViewById();
        initData();
    }


    @Override
    protected int getContentView() {
        return R.layout.ac_essential_information;
    }

    private void FindViewById() {
        mDate = (TextView) findViewById(R.id.date);
        mChoose_address = (TextView) findViewById(R.id.choose_address);
    }

    private void initData() {
    }
}
