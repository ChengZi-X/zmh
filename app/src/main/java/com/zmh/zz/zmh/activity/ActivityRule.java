package com.zmh.zz.zmh.activity;

import android.os.Bundle;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 活动规则
 */

public class ActivityRule extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("活动规则");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_activity_rule;
    }

    private void FindViewById() {
    }
}
