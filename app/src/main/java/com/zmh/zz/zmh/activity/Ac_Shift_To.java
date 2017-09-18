package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.view.View;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 资金转入
 */

public class Ac_Shift_To extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("转入");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_shift_to;
    }

    private void FindViewById() {
    }

    @Override
    public void onClick(View view) {

    }
}
