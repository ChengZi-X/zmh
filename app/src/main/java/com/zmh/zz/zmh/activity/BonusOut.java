package com.zmh.zz.zmh.activity;

import android.os.Bundle;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 奖金转出
 */

public class BonusOut extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("转出");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_bonus_out;
    }

    private void FindViewById() {
    }

}