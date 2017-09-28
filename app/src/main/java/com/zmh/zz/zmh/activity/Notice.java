package com.zmh.zz.zmh.activity;

import android.os.Bundle;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;


/**
 * 公告
 */

public class Notice extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("公告");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_notice;
    }

    private void FindViewById() {
    }
}